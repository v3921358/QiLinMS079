/*
修改by宗達 20160106 06:52
*/
var Message = new Array(
    "如果遇到不能点技能/能力值/不能进传送/不能点NPC,请在对话框打 @ea / @解卡 就可以了",
    "慎重声明：管理员不介入玩家纠纷，如有纷扰请自行协商解决，请勿波及管理员。",
	"禁止使用第三方程式、按键精灵，修改WZ、包括脚本挂机等一切非法方式进行游戏。",
	"禁止辱骂管理员，辱骂玩家，制造不实谣言攻击服务器，发现辱骂者封号处理。",
	"禁止游戏中现金交易，如实在有需要可以找员外担保交易，私下交易出现问题管理不予找回。",
	"禁止使用Bug，如游戏中发现重大Bug请立即回报给管理员，将有重大奖励。",
//	"承接072/079/085冒险岛一条龙服务，开服唯一联系QQ：【 1500663066 】 。",
	"物品交易请仔细核对，物品错卖给商人恕不补偿，请看清道具后再卖。",
	"账号请勿轻易给别人使用，造成被盗状况无法补偿。");
var setupTask;
function init() {
    scheduleNew();
}
function scheduleNew() {
    setupTask = em.schedule("start", 300000);
}
function cancelSchedule() {
    setupTask.cancel(true);
}
function start() {
    scheduleNew();
    em.broadcastYellowMsg("[ " + em.getServerName() + " ]" + Message[Math.floor(Math.random() * Message.length)]);
}