var status;
var chance;

function start() {
status = -1;
chance = (Math.random() * 122);
action(1, 0, 0);
}

function action(mode, type, selection) {
if (mode == 1)
status++;
else {
cm.dispose();
return;
}
if (status == 0) {
cm.sendAcceptDecline("你好！ 我運行的遊戲叫 #bC#ro#gl#do#br #rG#ga#dm#bb#rl#ge#k!你選擇你想要遊戲的顏色，並為每種顏色下注，然後看看球是否落在你的顏色上!");
}else if(status == 1){
//黑色 == 35%
//藍色 == 35%
//red == 15%
//紫色 == 10%
//綠色 == 5%
cm.sendSimple("請做出選擇: \r\n#e#L0#黑色 (25K 楓幣) \r\n#b#L1#藍色 (25K 楓幣) \r\n#r#L2#Red (45K 楓幣) \r\n#d#L3#紫色 (75k 楓幣) \r\n#g#L4#綠色 (125K 楓幣)");
}else if(selection == 0){
var chance = (Math.random() * 100);
if(cm.getMeso() >= 25000 && chance <= 40){
cm.sendOk("#e你的顏色選擇: 黑色 \r\n#e#k球滾動: 黑色 \r\n\r\n你贏了!! 你收到 25,000 楓幣!");
cm.gainMeso(25000);
cm.dispose();
}else if(cm.getMeso() >= 25000 && chance > 40 && chance <= 80){
cm.sendOk("#e你的顏色選擇: 黑色 \r\n#e#k球滾動: #b藍色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else if(cm.getMeso() >=25000 && chance > 80 && chance <= 105){
cm.sendOk("#e你的顏色選擇: 黑色 \r\n#e#k球滾動: #rRed \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else if(cm.getMeso() >= 25000 && chance > 105 && chance <= 122){
cm.sendOk("#e你的顏色選擇: 黑色 \r\n#e#k球滾動: #d紫色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else if(cm.getMeso() >= 25000 && chance > 95){
cm.sendOk("#e你的顏色選擇: 黑色 \r\n#e#k球滾動: #g綠色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else{
cm.sendOk("你的$不夠 楓幣 想賒帳...");
cm.dispose();
}
}else if(selection == 1){
var chance = (Math.random() * 100);
if(cm.getMeso() >= 25000 && chance <= 35){
cm.sendOk("#e你的顏色選擇: #b藍色 \r\n#e#k球滾動: #k黑色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else if(cm.getMeso() >= 25000 && chance > 35 && chance <= 70){
cm.sendOk("#e你的顏色選擇: #藍色 \r\n#e#k球滾動: #b藍色 \r\n\r\n你贏了!! 你收到 25,000 楓幣!");
cm.gainMeso(25000);
cm.dispose();
}else if(cm.getMeso() >=25000 && chance > 70 && chance <= 85){
cm.sendOk("#e你的顏色選擇: #b藍色 \r\n#e#k球滾動: #rRed \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else if(cm.getMeso() >= 25000 && chance > 85 && chance <= 95){
cm.sendOk("#e你的顏色選擇: #b藍色 \r\n#e#k球滾動: #d紫色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else if(cm.getMeso() >= 25000 && chance > 95){
cm.sendOk("#e你的顏色選擇: #b藍色 \r\n#e#k球滾動: #g綠色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-25000);
cm.dispose();
}else{
cm.sendOk("你的$不夠 楓幣 想賒帳...");
cm.dispose();
}
}else if(selection == 2){
var chance = (Math.random() * 100);
if(cm.getMeso() >= 45000 && chance <= 35){
cm.sendOk("#e你的顏色選擇: #rRed \r\n#e#k球滾動: #k黑色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-45000);
cm.dispose();
}else if(cm.getMeso() >= 45000 && chance > 35 && chance <= 70){
cm.sendOk("#e你的顏色選擇: #rRed \r\n#e#k球滾動: #b藍色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-45000);
cm.dispose();
}else if(cm.getMeso() >=45000 && chance > 70 && chance <= 85){
cm.sendOk("#e你的顏色選擇: #rRed \r\n#e#k球滾動: #rRed \r\n\r\n你贏了!! 你收到 45,000 楓幣!");
cm.gainMeso(45000);
cm.dispose();
}else if(cm.getMeso() >= 45000 && chance > 85 && chance <= 95){
cm.sendOk("#e你的顏色選擇: #rRed \r\n#e#k球滾動: #d紫色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-45000);
cm.dispose();
}else if(cm.getMeso() >= 45000 && chance > 95){
cm.sendOk("#e你的顏色選擇: #rRed \r\n#e#k球滾動: #g綠色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-45000);
cm.dispose();
}else{
cm.sendOk("你的$不夠 楓幣 想賒帳...");
cm.dispose();
}
}else if(selection == 3){
var chance = (Math.random() * 100);
if(cm.getMeso() >= 75000 && chance <= 35){
cm.sendOk("#e你的顏色選擇: #d紫色 \r\n#e#k球滾動: #k黑色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-75000);
cm.dispose();
}else if(cm.getMeso() >= 75000 && chance > 35 && chance <= 70){
cm.sendOk("#e你的顏色選擇: #d紫色 \r\n#e#k球滾動: #b藍色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-75000);
cm.dispose();
}else if(cm.getMeso() >=75000 && chance > 70 && chance <= 85){
cm.sendOk("#e你的顏色選擇: #d紫色 \r\n#e#k球滾動: #rRed \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-75000);
cm.dispose();
}else if(cm.getMeso() >= 75000 && chance > 85 && chance <= 95){
cm.sendOk("#e你的顏色選擇: #d紫色 \r\n#e#k球滾動: #d紫色 \r\n\r\n你贏了!! 你收到 75,000 楓幣!");
cm.gainMeso(75000);
cm.dispose();
}else if(cm.getMeso() >= 725000 && chance > 95){
cm.sendOk("#e你的顏色選擇: #d紫色 \r\n#e#k球滾動: #g綠色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-75000);
cm.dispose();
}else{
cm.sendOk("你的$不夠 楓幣 想賒帳...");
cm.dispose();
}

}else if(selection == 4){
var chance = (Math.random() * 100);
if(cm.getMeso() >= 125000 && chance <= 35){
cm.sendOk("#e你的顏色選擇: #g綠色 \r\n#e#k球滾動: #k黑色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-125000);
cm.dispose();
}else if(cm.getMeso() >= 125000 && chance > 35 && chance <= 70){
cm.sendOk("#e你的顏色選擇: #g綠色 \r\n#e#k球滾動: #b藍色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-125000);
cm.dispose();
}else if(cm.getMeso() >=125000 && chance > 70 && chance <= 85){
cm.sendOk("#e你的顏色選擇: #g綠色 \r\n#e#k球滾動: #rRed \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-125000);
cm.dispose();
}else if(cm.getMeso() >= 125000 && chance > 85 && chance <= 95){
cm.sendOk("#e你的顏色選擇: #g綠色 \r\n#e#k球滾動: #d紫色 \r\n\r\n你輸了!! 對不起，你可以再玩一次!");
cm.gainMeso(-125000);
cm.dispose();
}else if(cm.getMeso() >= 125000 && chance > 95){
cm.sendOk("#e你的顏色選擇: #g綠色 \r\n#e#k球滾動: #g綠色 \r\n\r\n你贏了!! 你收到 125,000 楓幣!");
cm.gainMeso(125000);
cm.dispose();
}else{
cm.sendOk("你的$不夠 楓幣 想賒帳...");
cm.dispose();
}
}
}