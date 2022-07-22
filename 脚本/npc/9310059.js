function start() {
    status = -1;

    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "\r\n\r\n#r赞助比例1:100点卷！\r\n#k\r\n"
			//text += "#e#r#L0#充值排行榜#l#n\r\n\r\n"
            text += "#e#r#L1#赞助网站#l#n\r\n\r\n"
		//	text += "#e#r#L2#【赞助领取-当前#g "+cm.getwzcz() +"#r点券尚未领取请点击领取】#l#n\r\n\r\n"
            text += "#e#r#L3#赞助礼包#l#n\r\n\r\n"
            cm.sendOk(text); 
		} else if (selection == 0) {
			cm.showcz();
		   cm.dispose();
        } else if (selection == 1) {
         cm.openWeb("http://new.shoukabao.cn/Payment/Service/eb81c45ec46083d9cbc89c884cd691e6");
		// cm.openWeb("http://WWW.BAIDU.COM");
		 cm.sendOk("充值完毕后,#r请单击确认返回，充值领取\r\n");
          status = -1;
        } else if (selection == 2) {
            if(cm.getwzcz()==0){
		cm.sendOk("您当前未兑现金额为"+ cm.getwzcz() +"点券 ，兑换失败！\r\n#r.");
		 status = -1;
		}else{
		var  j = cm.getwzcz();
		cm.setwzcz(-j);
		cm.gainDJ(j*1);
        cm.gaincz(+(j/100));	
		//********************************************************
		
		if(cm.gettgr()!=0){//有推广人
			cm.gaintgrjl(cm.gettgr(),+j*0.2);//5%
		}
		
		
		
		
		//***********************************
        cm.sendOk("兑现成功！获得"+ j*1 + "点卷！\r\n#r.");
		 status = -1;
        }
		} else if (selection == 3) {
		cm.openNpc(9900004, "充值礼包");
		} else if (selection == 4) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<200) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 1) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainMeso(1500000);//给金币
				cm.sendOk("成功兑换#k")
				status = -1;
			}
		} else if (selection == 5) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<500) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 2) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(4001126, 5000);//物品
				cm.gainItem(3010070, 1);//物品
				cm.sendOk("成功兑换#k")
				status = -1;
			}
		} else if (selection == 6) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<1000) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 3) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(4001126, 9999);//物品
				cm.gainItem(4001126, 1);//物品
				cm.gainItem(1102086, 1);//物品
				cm.sendOk("成功兑换#k")
				status = -1;
			}
			} else if (selection == 7) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<5000) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 4) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(4001126, 9999);//物品
				cm.gainItem(4001126, 9999);//物品
				cm.gainItem(4001126, 2);//物品
				cm.gainItem(1102041, 1);//物品
				cm.sendOk("成功兑换#k")
				status = -1;
			}
				} else if (selection == 8) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<10000) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 5) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(1102163, 1);//物品
				cm.sendOk("成功兑换#k")
				status = -1;
			}
			} else if (selection == 9) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<50000) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 5) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(1102163, 1);//物品
				cm.sendOk("成功兑换#k")
				status = -1;
			}
			} else if (selection == 10) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<100000) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 6) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(1142742, 1);//物品
				cm.sendOk("成功兑换#k")
				status = -1;
			}
			} else if (selection == 11) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法兑换");
				cm.dispose();
			} else if (cm.getcz()<500000) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("没充值够数！请充值吧！\r\n当前充值："+cm.getcz()+"元！");
				cm.dispose();
			} else if (!cm.getczlz() == 6) {//判断累积到100元以上 第一个礼包没领取
				cm.sendOk("请问你有什么事吗？");
				cm.dispose();
			} else{

				cm.sendOk("请联系群主吧#k")
				status = -1;
			}
    }
}}