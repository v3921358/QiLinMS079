/* Boss Kitty
	Zipangu : Showa Town (801000000)
	
	Quiz for quest 8012 (Sakura, the Kitty, and the Orange Marble)
*/

var status = -1;
var questions = new Array("下列物品中何者不是貍貓所掉出的物品?", "你知道昭和蔬果店的老闆叫什麼名字嗎?", "昭和電影院前的NPC是下列何者?", "古代神社理，寫著『香菇（???）』(平假名)的地方有幾個?", "古代神社販售物品中，何者可提升攻擊力?", "聯結西門町與江戶村的NPC是誰?", "下列物品中，何者不是盜賊們所掉出的道具?", "下列何者不是實際存在的物品?", "下列何者是實際存在的道具?", "下列何者不是古代神社元泰所販售的拉麵?", "昭和當魚外面寫著什麼呢?", "下列武器中，何者的說明有誤?");;
var answers = new Array(new Array("貍貓柴薪", "獨角獅硬角", "紅色磚頭"), new Array("莎美", "卡美", "由美"), new Array("涼子", "明日香", "繪里香"), new Array("8個", "7個", "6個"), new Array("章魚燒", "日式炒麵", "黑輪"), new Array("麻雀", "鵜鵠鳥", "烏鴉"), new Array("五角徽章", "束腹", "鍍金項鍊"), new Array("冷凍金槍魚", "清酒", "蒼蠅拍"), new Array("雪狐的犬齒", "遊魂的捧花", "雪狐的尾巴"), new Array("烤豬肉拉麵", "鹽味拉麵", "香菇特製味噌拉麵"), new Array("千客萬來", "商賣繁盛", "買收販售"), new Array("木精靈槍－劍士專用武器", "橡皮榔頭－單手劍", "日本地圖－可裝備等級50級"));;
var correctAnswer = new Array(1, 2, 2, 2, 0, 1, 1, 2, 2, 2, 1, 0);
var questionNum;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        if (cm.getQuestStatus(8012) == 1 && !cm.haveItem(4031064)) { //quest in progress
            cm.sendYesNo("怎麼了你要準備挑戰我的任務了??");
        } else { //quest not started or already completed
            cm.sendOk("喵~~~");
            cm.safeDispose();
        }
    } else if (status == 1) {
        var hasChicken = cm.haveItem(2020001, 300);

        if (!hasChicken) {
            cm.sendOk("請先交出來300個炸機！");
            cm.safeDispose();
        } else {
            cm.gainItem(2020001, -300)
            cm.sendNext("做得好！接下來我會問幾點問題要是你能答對的話我就會給你獎勵。你準備好了嗎？");
        }
    } else if (status == 7) { //2-6 are the questions
        if (selection != correctAnswer.pop()) {
            cm.sendNext("痾...很不幸的是你答錯了題目因此請再給我300個炸機重新來過吧~")
            cm.safeDispose();
        } else {
            cm.sendNext("做得不錯....")
        }
    } else if (status == 8) { //gain marble
        cm.gainItem(4031064, 1);
        cm.sendOk("哇！天才神童呢看來你現在不需要我了，我們的交易到此結束。");
        cm.safeDispose();
    } else if (status >= 2 && status <= 6 && mode == 1) { //questions
        var cont = true;
        if (status > 2) {
            if (selection != correctAnswer.pop()) {
                cm.sendNext("痾...很不幸的是你答錯了題目因此請再給我300個炸機重新來過吧~")
                cm.safeDispose();
                cont = false;
            }
        }

        if (cont) {
            questionNum = Math.floor(Math.random() * questions.length);
            if (questionNum != (questions.length - 1)) {
                var temp;
                temp = questions[questionNum];
                questions[questionNum] = questions[questions.length - 1];
                questions[questions.length - 1] = temp;
                temp = answers[questionNum];
                answers[questionNum] = answers[questions.length - 1];
                answers[questions.length - 1] = temp;
                temp = correctAnswer[questionNum];
                correctAnswer[questionNum] = correctAnswer[questions.length - 1];
                correctAnswer[questions.length - 1] = temp;
            }

            var question = questions.pop();
            var answer = answers.pop();
            var prompt = "第" + (status - 1) + "題: " + question;

            for (var i = 0; i < answer.length; i++) {
                prompt += "\r\n#b#L" + i + "#" + answer[i] + "#l#k"
            }

            cm.sendSimple(prompt);
        }
    }
}