package client;

import client.messages.CommandProcessorUtil;
import constants.GameConstants;
import gui.Qhms;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import server.life.MapleMonster;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.quest.MapleQuest;
import tools.HexTool;
import tools.MaplePacketCreator;
import tools.data.MaplePacketLittleEndianWriter;

/**
 *
 * @author Administrator
 */
public class DebugWindow extends javax.swing.JFrame {

    private MapleClient c;

    public DebugWindow() {
        initComponents();
    }

    public MapleClient getC() {
        return this.c;
    }

    public void setC(MapleClient c) {
        this.c = c;
        if (c.getPlayer() != null) {
            setTitle("玩家操作面板: " + c.getPlayer().getName() + " ");

        }
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/图片/pp/2.png"));
        setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        封包 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        升级 = new javax.swing.JButton();
        无敌 = new javax.swing.JButton();
        SP = new javax.swing.JButton();
        AP = new javax.swing.JButton();
        刷新 = new javax.swing.JButton();
        清怪 = new javax.swing.JButton();
        清物 = new javax.swing.JButton();
        坐标 = new javax.swing.JButton();
        满技能 = new javax.swing.JButton();
        满属性 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        战士 = new javax.swing.JButton();
        剑客 = new javax.swing.JButton();
        勇士 = new javax.swing.JButton();
        英雄 = new javax.swing.JButton();
        准骑士 = new javax.swing.JButton();
        骑士 = new javax.swing.JButton();
        圣骑士 = new javax.swing.JButton();
        枪战士 = new javax.swing.JButton();
        龙骑士 = new javax.swing.JButton();
        黑骑士 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        魔法师 = new javax.swing.JButton();
        火毒法师 = new javax.swing.JButton();
        火毒巫师 = new javax.swing.JButton();
        火毒魔导师 = new javax.swing.JButton();
        冰雷法师 = new javax.swing.JButton();
        冰雷巫师 = new javax.swing.JButton();
        冰雷魔导师 = new javax.swing.JButton();
        牧师 = new javax.swing.JButton();
        祭司 = new javax.swing.JButton();
        主教 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        弓箭手 = new javax.swing.JButton();
        火毒法师1 = new javax.swing.JButton();
        射手 = new javax.swing.JButton();
        神射手 = new javax.swing.JButton();
        弩弓手 = new javax.swing.JButton();
        游侠 = new javax.swing.JButton();
        箭神 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        飞侠 = new javax.swing.JButton();
        刺客 = new javax.swing.JButton();
        无影人 = new javax.swing.JButton();
        隐士 = new javax.swing.JButton();
        侠盗 = new javax.swing.JButton();
        独行客 = new javax.swing.JButton();
        侠客 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        海盗 = new javax.swing.JButton();
        拳手 = new javax.swing.JButton();
        斗士 = new javax.swing.JButton();
        冲锋队长 = new javax.swing.JButton();
        火枪手 = new javax.swing.JButton();
        大副 = new javax.swing.JButton();
        船长 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        魂骑士1 = new javax.swing.JButton();
        魂骑士2 = new javax.swing.JButton();
        魂骑士3 = new javax.swing.JButton();
        炎术士1 = new javax.swing.JButton();
        炎术士2 = new javax.swing.JButton();
        炎术士3 = new javax.swing.JButton();
        风灵使者1 = new javax.swing.JButton();
        风灵使者3 = new javax.swing.JButton();
        风灵使者2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        夜行者1 = new javax.swing.JButton();
        夜行者2 = new javax.swing.JButton();
        夜行者3 = new javax.swing.JButton();
        奇袭者3 = new javax.swing.JButton();
        奇袭者2 = new javax.swing.JButton();
        奇袭者1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        战神1 = new javax.swing.JButton();
        战神2 = new javax.swing.JButton();
        战神3 = new javax.swing.JButton();
        战神4 = new javax.swing.JButton();
        任务开始 = new javax.swing.JButton();
        金币 = new javax.swing.JButton();
        点券 = new javax.swing.JButton();
        抵用券 = new javax.swing.JButton();
        刷物品 = new javax.swing.JButton();
        任务代码 = new javax.swing.JTextField();
        任务完成 = new javax.swing.JButton();
        刷新11 = new javax.swing.JButton();
        物品数量 = new javax.swing.JTextField();
        物品代码 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, -1, -1));

        封包.setColumns(20);
        封包.setRows(5);
        jScrollPane2.setViewportView(封包);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 420, 90));

        jButton1.setText("发送封包");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 420, 30));

        升级.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        升级.setText("升级");
        升级.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                升级ActionPerformed(evt);
            }
        });
        jPanel1.add(升级, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 140, -1));

        无敌.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        无敌.setText("无敌");
        无敌.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                无敌ActionPerformed(evt);
            }
        });
        jPanel1.add(无敌, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 140, -1));

        SP.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        SP.setText("SP+10");
        SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPActionPerformed(evt);
            }
        });
        jPanel1.add(SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 140, -1));

        AP.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        AP.setText("AP+10");
        AP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APActionPerformed(evt);
            }
        });
        jPanel1.add(AP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 140, -1));

        刷新.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        刷新.setText("刷新");
        刷新.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                刷新ActionPerformed(evt);
            }
        });
        jPanel1.add(刷新, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 140, -1));

        清怪.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        清怪.setText("清怪");
        清怪.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                清怪ActionPerformed(evt);
            }
        });
        jPanel1.add(清怪, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, -1));

        清物.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        清物.setText("清物");
        清物.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                清物ActionPerformed(evt);
            }
        });
        jPanel1.add(清物, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        坐标.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        坐标.setText("坐标");
        坐标.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                坐标ActionPerformed(evt);
            }
        });
        jPanel1.add(坐标, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 140, -1));

        满技能.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        满技能.setText("满技能");
        满技能.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                满技能ActionPerformed(evt);
            }
        });
        jPanel1.add(满技能, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        满属性.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        满属性.setText("满属性");
        满属性.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                满属性ActionPerformed(evt);
            }
        });
        jPanel1.add(满属性, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        战士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        战士.setText("战士");
        战士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                战士ActionPerformed(evt);
            }
        });
        jPanel3.add(战士, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        剑客.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        剑客.setText("剑客");
        剑客.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                剑客ActionPerformed(evt);
            }
        });
        jPanel3.add(剑客, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        勇士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        勇士.setText("勇士");
        勇士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                勇士ActionPerformed(evt);
            }
        });
        jPanel3.add(勇士, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        英雄.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        英雄.setText("英雄");
        英雄.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                英雄ActionPerformed(evt);
            }
        });
        jPanel3.add(英雄, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        准骑士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        准骑士.setText("准骑士");
        准骑士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                准骑士ActionPerformed(evt);
            }
        });
        jPanel3.add(准骑士, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        骑士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        骑士.setText("骑士");
        骑士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                骑士ActionPerformed(evt);
            }
        });
        jPanel3.add(骑士, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        圣骑士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        圣骑士.setText("圣骑士");
        圣骑士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                圣骑士ActionPerformed(evt);
            }
        });
        jPanel3.add(圣骑士, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        枪战士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        枪战士.setText("枪战士");
        枪战士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                枪战士ActionPerformed(evt);
            }
        });
        jPanel3.add(枪战士, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, -1));

        龙骑士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        龙骑士.setText("龙骑士");
        龙骑士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                龙骑士ActionPerformed(evt);
            }
        });
        jPanel3.add(龙骑士, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 120, -1));

        黑骑士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        黑骑士.setText("黑骑士");
        黑骑士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                黑骑士ActionPerformed(evt);
            }
        });
        jPanel3.add(黑骑士, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 120, -1));

        jTabbedPane1.addTab("战士", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        魔法师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        魔法师.setText("魔法师");
        魔法师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                魔法师ActionPerformed(evt);
            }
        });
        jPanel4.add(魔法师, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        火毒法师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        火毒法师.setText("火毒法师");
        火毒法师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                火毒法师ActionPerformed(evt);
            }
        });
        jPanel4.add(火毒法师, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        火毒巫师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        火毒巫师.setText("火毒巫师");
        火毒巫师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                火毒巫师ActionPerformed(evt);
            }
        });
        jPanel4.add(火毒巫师, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        火毒魔导师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        火毒魔导师.setText("火毒魔导师");
        火毒魔导师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                火毒魔导师ActionPerformed(evt);
            }
        });
        jPanel4.add(火毒魔导师, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        冰雷法师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        冰雷法师.setText("冰雷法师");
        冰雷法师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                冰雷法师ActionPerformed(evt);
            }
        });
        jPanel4.add(冰雷法师, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        冰雷巫师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        冰雷巫师.setText("冰雷巫师");
        冰雷巫师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                冰雷巫师ActionPerformed(evt);
            }
        });
        jPanel4.add(冰雷巫师, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        冰雷魔导师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        冰雷魔导师.setText("冰雷魔导师");
        冰雷魔导师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                冰雷魔导师ActionPerformed(evt);
            }
        });
        jPanel4.add(冰雷魔导师, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        牧师.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        牧师.setText("牧师");
        牧师.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                牧师ActionPerformed(evt);
            }
        });
        jPanel4.add(牧师, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, -1));

        祭司.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        祭司.setText("祭司");
        祭司.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                祭司ActionPerformed(evt);
            }
        });
        jPanel4.add(祭司, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 120, -1));

        主教.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        主教.setText("主教");
        主教.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                主教ActionPerformed(evt);
            }
        });
        jPanel4.add(主教, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 120, -1));

        jTabbedPane1.addTab("魔法师", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        弓箭手.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        弓箭手.setText("弓箭手");
        弓箭手.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                弓箭手ActionPerformed(evt);
            }
        });
        jPanel5.add(弓箭手, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        火毒法师1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        火毒法师1.setText("猎人");
        火毒法师1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                火毒法师1ActionPerformed(evt);
            }
        });
        jPanel5.add(火毒法师1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        射手.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        射手.setText("射手");
        射手.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                射手ActionPerformed(evt);
            }
        });
        jPanel5.add(射手, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        神射手.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        神射手.setText("神射手");
        神射手.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                神射手ActionPerformed(evt);
            }
        });
        jPanel5.add(神射手, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        弩弓手.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        弩弓手.setText("弩弓手");
        弩弓手.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                弩弓手ActionPerformed(evt);
            }
        });
        jPanel5.add(弩弓手, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        游侠.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        游侠.setText("游侠");
        游侠.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                游侠ActionPerformed(evt);
            }
        });
        jPanel5.add(游侠, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        箭神.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        箭神.setText("箭神");
        箭神.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                箭神ActionPerformed(evt);
            }
        });
        jPanel5.add(箭神, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        jTabbedPane1.addTab("弓箭手", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        飞侠.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        飞侠.setText("飞侠");
        飞侠.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                飞侠ActionPerformed(evt);
            }
        });
        jPanel6.add(飞侠, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        刺客.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        刺客.setText("刺客");
        刺客.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                刺客ActionPerformed(evt);
            }
        });
        jPanel6.add(刺客, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        无影人.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        无影人.setText("无影人");
        无影人.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                无影人ActionPerformed(evt);
            }
        });
        jPanel6.add(无影人, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        隐士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        隐士.setText("隐士");
        隐士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                隐士ActionPerformed(evt);
            }
        });
        jPanel6.add(隐士, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        侠盗.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        侠盗.setText("侠盗");
        侠盗.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                侠盗ActionPerformed(evt);
            }
        });
        jPanel6.add(侠盗, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        独行客.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        独行客.setText("独行客");
        独行客.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                独行客ActionPerformed(evt);
            }
        });
        jPanel6.add(独行客, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        侠客.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        侠客.setText("侠客");
        侠客.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                侠客ActionPerformed(evt);
            }
        });
        jPanel6.add(侠客, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        jTabbedPane1.addTab("飞侠", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        海盗.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        海盗.setText("海盗");
        海盗.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                海盗ActionPerformed(evt);
            }
        });
        jPanel7.add(海盗, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        拳手.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        拳手.setText("拳手");
        拳手.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                拳手ActionPerformed(evt);
            }
        });
        jPanel7.add(拳手, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        斗士.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        斗士.setText("斗士");
        斗士.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                斗士ActionPerformed(evt);
            }
        });
        jPanel7.add(斗士, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        冲锋队长.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        冲锋队长.setText("冲锋队长");
        冲锋队长.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                冲锋队长ActionPerformed(evt);
            }
        });
        jPanel7.add(冲锋队长, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        火枪手.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        火枪手.setText("火枪手");
        火枪手.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                火枪手ActionPerformed(evt);
            }
        });
        jPanel7.add(火枪手, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        大副.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        大副.setText("大副");
        大副.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                大副ActionPerformed(evt);
            }
        });
        jPanel7.add(大副, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        船长.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        船长.setText("船长");
        船长.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                船长ActionPerformed(evt);
            }
        });
        jPanel7.add(船长, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        jTabbedPane1.addTab("海盗", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        魂骑士1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        魂骑士1.setText("魂骑士1");
        魂骑士1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                魂骑士1ActionPerformed(evt);
            }
        });
        jPanel8.add(魂骑士1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        魂骑士2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        魂骑士2.setText("魂骑士2");
        魂骑士2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                魂骑士2ActionPerformed(evt);
            }
        });
        jPanel8.add(魂骑士2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        魂骑士3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        魂骑士3.setText("魂骑士3");
        魂骑士3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                魂骑士3ActionPerformed(evt);
            }
        });
        jPanel8.add(魂骑士3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        炎术士1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        炎术士1.setText("炎术士1");
        炎术士1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                炎术士1ActionPerformed(evt);
            }
        });
        jPanel8.add(炎术士1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 120, -1));

        炎术士2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        炎术士2.setText("炎术士2");
        炎术士2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                炎术士2ActionPerformed(evt);
            }
        });
        jPanel8.add(炎术士2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        炎术士3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        炎术士3.setText("炎术士3");
        炎术士3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                炎术士3ActionPerformed(evt);
            }
        });
        jPanel8.add(炎术士3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        风灵使者1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        风灵使者1.setText("风灵使者1");
        风灵使者1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                风灵使者1ActionPerformed(evt);
            }
        });
        jPanel8.add(风灵使者1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 120, -1));

        风灵使者3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        风灵使者3.setText("风灵使者3");
        风灵使者3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                风灵使者3ActionPerformed(evt);
            }
        });
        jPanel8.add(风灵使者3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 120, -1));

        风灵使者2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        风灵使者2.setText("风灵使者2");
        风灵使者2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                风灵使者2ActionPerformed(evt);
            }
        });
        jPanel8.add(风灵使者2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, -1));

        jTabbedPane1.addTab("骑士团", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        夜行者1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        夜行者1.setText("夜行者1");
        夜行者1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                夜行者1ActionPerformed(evt);
            }
        });
        jPanel9.add(夜行者1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        夜行者2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        夜行者2.setText("夜行者2");
        夜行者2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                夜行者2ActionPerformed(evt);
            }
        });
        jPanel9.add(夜行者2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        夜行者3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        夜行者3.setText("夜行者3");
        夜行者3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                夜行者3ActionPerformed(evt);
            }
        });
        jPanel9.add(夜行者3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        奇袭者3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        奇袭者3.setText("奇袭者3");
        奇袭者3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                奇袭者3ActionPerformed(evt);
            }
        });
        jPanel9.add(奇袭者3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        奇袭者2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        奇袭者2.setText("奇袭者2");
        奇袭者2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                奇袭者2ActionPerformed(evt);
            }
        });
        jPanel9.add(奇袭者2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        奇袭者1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        奇袭者1.setText("奇袭者1");
        奇袭者1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                奇袭者1ActionPerformed(evt);
            }
        });
        jPanel9.add(奇袭者1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 120, -1));

        jTabbedPane1.addTab("*", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        战神1.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        战神1.setText("战神1");
        战神1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                战神1ActionPerformed(evt);
            }
        });
        jPanel10.add(战神1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        战神2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        战神2.setText("战神2");
        战神2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                战神2ActionPerformed(evt);
            }
        });
        jPanel10.add(战神2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        战神3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        战神3.setText("战神3");
        战神3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                战神3ActionPerformed(evt);
            }
        });
        jPanel10.add(战神3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        战神4.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        战神4.setText("战神4");
        战神4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                战神4ActionPerformed(evt);
            }
        });
        jPanel10.add(战神4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        jTabbedPane1.addTab("战神", jPanel10);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 170));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 420, 170));

        任务开始.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        任务开始.setText("任务开始");
        任务开始.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                任务开始ActionPerformed(evt);
            }
        });
        jPanel1.add(任务开始, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, -1));

        金币.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        金币.setText("金币");
        金币.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                金币ActionPerformed(evt);
            }
        });
        jPanel1.add(金币, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 140, -1));

        点券.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        点券.setText("点券");
        点券.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                点券ActionPerformed(evt);
            }
        });
        jPanel1.add(点券, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 140, -1));

        抵用券.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        抵用券.setText("抵用券");
        抵用券.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                抵用券ActionPerformed(evt);
            }
        });
        jPanel1.add(抵用券, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 140, -1));

        刷物品.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        刷物品.setText("刷物品");
        刷物品.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                刷物品ActionPerformed(evt);
            }
        });
        jPanel1.add(刷物品, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, -1));

        任务代码.setText("任务代码");
        jPanel1.add(任务代码, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 130, -1));

        任务完成.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        任务完成.setText("任务完成");
        任务完成.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                任务完成ActionPerformed(evt);
            }
        });
        jPanel1.add(任务完成, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 140, -1));

        刷新11.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        刷新11.setText("没用的");
        刷新11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                刷新11ActionPerformed(evt);
            }
        });
        jPanel1.add(刷新11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, -1));

        物品数量.setText("10");
        jPanel1.add(物品数量, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 130, -1));

        物品代码.setText("2000000");
        jPanel1.add(物品代码, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 130, -1));

        jLabel2.setFont(new java.awt.Font("幼圆", 0, 18)); // NOI18N
        jLabel2.setText("准备发送；");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        byte[] data = HexTool.getByteArrayFromHexString(this.封包.getText());
        //this.jTextArea2.setText(null);
        this.jLabel1.setText(null);
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        int packetheader = Integer.parseInt(this.封包.getText());
        String packet_in = " 00 00 00 00 00 00 00 00 00 ";
        mplew.writeShort(packetheader);
        mplew.write(HexTool.getByteArrayFromHexString(packet_in));
        mplew.writeZeroBytes(20);
        c.sendPacket(mplew.getPacket());
        //c.getPlayer().dropMessage(6,"已传送封包[" +packetheader + "] ");
        c.getPlayer().dropMessage(6, "已传送封包[" + packetheader + "][" + mplew.getPacket().length + "] : " + mplew.toString());
        this.封包.setText("" + (Integer.parseInt(this.封包.getText()) + 1) + "");
        System.err.println("" + c.getPlayer().getName() + " 已发送：" + (Integer.parseInt(this.封包.getText()) - 1) + "");
        this.jButton1.setText("已发送：" + (Integer.parseInt(this.封包.getText()) - 1) + "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void 升级ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_升级ActionPerformed
        c.getPlayer().dropMessage(6, "[等级上升]:成功增加等级");
        c.getPlayer().setExp(0);
        c.getPlayer().updateSingleStat(MapleStat.EXP, 0);
        if (c.getPlayer().getLevel() < 200) {
            c.getPlayer().gainExp(GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) + 1, true, false, true);
        }
    }//GEN-LAST:event_升级ActionPerformed

    private void 无敌ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_无敌ActionPerformed
        MapleCharacter player = c.getPlayer();
        if (player.isInvincible()) {
            player.setInvincible(false);
            player.dropMessage(6, "[无敌状态]:已经关闭");
        } else {
            player.setInvincible(true);
            player.dropMessage(6, "[无敌状态]:已经开启.");
        }
    }//GEN-LAST:event_无敌ActionPerformed

    private void SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPActionPerformed
        c.getPlayer().gainSP((short) 10);
    }//GEN-LAST:event_SPActionPerformed

    private void APActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APActionPerformed
        c.getPlayer().gainAp((short) 10);
    }//GEN-LAST:event_APActionPerformed

    private void 刷新ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_刷新ActionPerformed
        MapleCharacter player = c.getPlayer();
        c.sendPacket(MaplePacketCreator.getCharInfo(player));
        player.getMap().removePlayer(player);
        player.getMap().addPlayer(player);
    }//GEN-LAST:event_刷新ActionPerformed

    private void 清怪ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_清怪ActionPerformed
        MapleMap map = c.getPlayer().getMap();
        double range = Double.POSITIVE_INFINITY;
        boolean drop = false;
        MapleMonster mob;
        List<MapleMapObject> monsters = map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
        for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            mob = (MapleMonster) monstermo;
            map.killMonster(mob, c.getPlayer(), true, false, (byte) 1);
        }
    }//GEN-LAST:event_清怪ActionPerformed

    private void 清物ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_清物ActionPerformed
        c.getPlayer().getMap().removeDrops();
    }//GEN-LAST:event_清物ActionPerformed

    private void 坐标ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_坐标ActionPerformed
        Point pos = c.getPlayer().getPosition();
        c.getPlayer().dropMessage(6, "X: " + pos.x + " | Y: " + pos.y + " | RX0: " + (pos.x + 50) + " | RX1: " + (pos.x - 50) + " | FH: " + c.getPlayer().getFH() + "| CY:" + pos.y);

    }//GEN-LAST:event_坐标ActionPerformed

    private void 满技能ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_满技能ActionPerformed
        MapleCharacter player = c.getPlayer();
        player.maxSkills();
    }//GEN-LAST:event_满技能ActionPerformed

    private void 满属性ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_满属性ActionPerformed
        MapleCharacter player = c.getPlayer();
        player.getStat().setMaxHp((short) 30000);
        player.getStat().setMaxMp((short) 30000);
        player.getStat().setStr(Short.MAX_VALUE);
        player.getStat().setDex(Short.MAX_VALUE);
        player.getStat().setInt(Short.MAX_VALUE);
        player.getStat().setLuk(Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.MAXHP, 30000);
        player.updateSingleStat(MapleStat.MAXMP, 30000);
        player.updateSingleStat(MapleStat.STR, Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.DEX, Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.INT, Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.LUK, Short.MAX_VALUE);
    }//GEN-LAST:event_满属性ActionPerformed

    private void 战士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_战士ActionPerformed
        c.getPlayer().changeJob(100);
    }//GEN-LAST:event_战士ActionPerformed

    private void 魂骑士1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_魂骑士1ActionPerformed
        c.getPlayer().changeJob(1100);
    }//GEN-LAST:event_魂骑士1ActionPerformed

    private void 剑客ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_剑客ActionPerformed
        c.getPlayer().changeJob(110);
    }//GEN-LAST:event_剑客ActionPerformed

    private void 勇士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_勇士ActionPerformed
        c.getPlayer().changeJob(111);
    }//GEN-LAST:event_勇士ActionPerformed

    private void 英雄ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_英雄ActionPerformed
        c.getPlayer().changeJob(112);
    }//GEN-LAST:event_英雄ActionPerformed

    private void 准骑士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_准骑士ActionPerformed
        c.getPlayer().changeJob(120);
    }//GEN-LAST:event_准骑士ActionPerformed

    private void 骑士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_骑士ActionPerformed
        c.getPlayer().changeJob(121);
    }//GEN-LAST:event_骑士ActionPerformed

    private void 圣骑士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_圣骑士ActionPerformed
        c.getPlayer().changeJob(122);
    }//GEN-LAST:event_圣骑士ActionPerformed

    private void 枪战士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_枪战士ActionPerformed
        c.getPlayer().changeJob(130);
    }//GEN-LAST:event_枪战士ActionPerformed

    private void 龙骑士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_龙骑士ActionPerformed
        c.getPlayer().changeJob(131);
    }//GEN-LAST:event_龙骑士ActionPerformed

    private void 黑骑士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_黑骑士ActionPerformed
        c.getPlayer().changeJob(132);
    }//GEN-LAST:event_黑骑士ActionPerformed

    private void 魔法师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_魔法师ActionPerformed
        c.getPlayer().changeJob(200);
    }//GEN-LAST:event_魔法师ActionPerformed

    private void 火毒法师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_火毒法师ActionPerformed
        c.getPlayer().changeJob(210);
    }//GEN-LAST:event_火毒法师ActionPerformed

    private void 火毒巫师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_火毒巫师ActionPerformed
        c.getPlayer().changeJob(211);
    }//GEN-LAST:event_火毒巫师ActionPerformed

    private void 火毒魔导师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_火毒魔导师ActionPerformed
        c.getPlayer().changeJob(212);
    }//GEN-LAST:event_火毒魔导师ActionPerformed

    private void 冰雷法师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_冰雷法师ActionPerformed
        c.getPlayer().changeJob(220);
    }//GEN-LAST:event_冰雷法师ActionPerformed

    private void 冰雷巫师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_冰雷巫师ActionPerformed
        c.getPlayer().changeJob(221);
    }//GEN-LAST:event_冰雷巫师ActionPerformed

    private void 冰雷魔导师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_冰雷魔导师ActionPerformed
        c.getPlayer().changeJob(222);
    }//GEN-LAST:event_冰雷魔导师ActionPerformed

    private void 牧师ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_牧师ActionPerformed
        c.getPlayer().changeJob(230);
    }//GEN-LAST:event_牧师ActionPerformed

    private void 祭司ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_祭司ActionPerformed
        c.getPlayer().changeJob(231);
    }//GEN-LAST:event_祭司ActionPerformed

    private void 主教ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_主教ActionPerformed
        c.getPlayer().changeJob(232);
    }//GEN-LAST:event_主教ActionPerformed

    private void 弓箭手ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_弓箭手ActionPerformed
        c.getPlayer().changeJob(300);
    }//GEN-LAST:event_弓箭手ActionPerformed

    private void 火毒法师1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_火毒法师1ActionPerformed
        c.getPlayer().changeJob(310);
    }//GEN-LAST:event_火毒法师1ActionPerformed

    private void 射手ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_射手ActionPerformed
        c.getPlayer().changeJob(311);
    }//GEN-LAST:event_射手ActionPerformed

    private void 神射手ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_神射手ActionPerformed
        c.getPlayer().changeJob(312);
    }//GEN-LAST:event_神射手ActionPerformed

    private void 弩弓手ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_弩弓手ActionPerformed
        c.getPlayer().changeJob(320);
    }//GEN-LAST:event_弩弓手ActionPerformed

    private void 游侠ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_游侠ActionPerformed
        c.getPlayer().changeJob(321);
    }//GEN-LAST:event_游侠ActionPerformed

    private void 箭神ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_箭神ActionPerformed
        c.getPlayer().changeJob(322);
    }//GEN-LAST:event_箭神ActionPerformed

    private void 飞侠ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_飞侠ActionPerformed
        c.getPlayer().changeJob(400);
    }//GEN-LAST:event_飞侠ActionPerformed

    private void 刺客ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_刺客ActionPerformed
        c.getPlayer().changeJob(410);
    }//GEN-LAST:event_刺客ActionPerformed

    private void 无影人ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_无影人ActionPerformed
        c.getPlayer().changeJob(411);
    }//GEN-LAST:event_无影人ActionPerformed

    private void 隐士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_隐士ActionPerformed
        c.getPlayer().changeJob(412);
    }//GEN-LAST:event_隐士ActionPerformed

    private void 侠盗ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_侠盗ActionPerformed
        c.getPlayer().changeJob(422);
    }//GEN-LAST:event_侠盗ActionPerformed

    private void 独行客ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_独行客ActionPerformed
        c.getPlayer().changeJob(421);
    }//GEN-LAST:event_独行客ActionPerformed

    private void 侠客ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_侠客ActionPerformed
        c.getPlayer().changeJob(420);
    }//GEN-LAST:event_侠客ActionPerformed

    private void 海盗ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_海盗ActionPerformed
        c.getPlayer().changeJob(500);
    }//GEN-LAST:event_海盗ActionPerformed

    private void 拳手ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_拳手ActionPerformed
        c.getPlayer().changeJob(510);
    }//GEN-LAST:event_拳手ActionPerformed

    private void 斗士ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_斗士ActionPerformed
        c.getPlayer().changeJob(511);
    }//GEN-LAST:event_斗士ActionPerformed

    private void 冲锋队长ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_冲锋队长ActionPerformed
        c.getPlayer().changeJob(512);
    }//GEN-LAST:event_冲锋队长ActionPerformed

    private void 火枪手ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_火枪手ActionPerformed
        c.getPlayer().changeJob(520);
    }//GEN-LAST:event_火枪手ActionPerformed

    private void 大副ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_大副ActionPerformed
        c.getPlayer().changeJob(521);
    }//GEN-LAST:event_大副ActionPerformed

    private void 船长ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_船长ActionPerformed
        c.getPlayer().changeJob(522);
    }//GEN-LAST:event_船长ActionPerformed

    private void 任务开始ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_任务开始ActionPerformed
        boolean a = this.任务代码.getText().matches("[0-9]+");
        if (a) {
            MapleQuest.getInstance(Integer.parseInt(任务代码.getText())).forceStart(c.getPlayer(), 0, null);
        }
    }//GEN-LAST:event_任务开始ActionPerformed

    private void 魂骑士2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_魂骑士2ActionPerformed
        c.getPlayer().changeJob(1110);
    }//GEN-LAST:event_魂骑士2ActionPerformed

    private void 魂骑士3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_魂骑士3ActionPerformed
        c.getPlayer().changeJob(1111);
    }//GEN-LAST:event_魂骑士3ActionPerformed

    private void 炎术士1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_炎术士1ActionPerformed
        c.getPlayer().changeJob(1200);
    }//GEN-LAST:event_炎术士1ActionPerformed

    private void 炎术士2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_炎术士2ActionPerformed
        c.getPlayer().changeJob(1210);
    }//GEN-LAST:event_炎术士2ActionPerformed

    private void 炎术士3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_炎术士3ActionPerformed
        c.getPlayer().changeJob(1211);
    }//GEN-LAST:event_炎术士3ActionPerformed

    private void 风灵使者1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_风灵使者1ActionPerformed
        c.getPlayer().changeJob(1300);
    }//GEN-LAST:event_风灵使者1ActionPerformed

    private void 风灵使者3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_风灵使者3ActionPerformed
        c.getPlayer().changeJob(1311);
    }//GEN-LAST:event_风灵使者3ActionPerformed

    private void 风灵使者2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_风灵使者2ActionPerformed
        c.getPlayer().changeJob(1310);
    }//GEN-LAST:event_风灵使者2ActionPerformed

    private void 夜行者1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_夜行者1ActionPerformed
        c.getPlayer().changeJob(1400);
    }//GEN-LAST:event_夜行者1ActionPerformed

    private void 夜行者2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_夜行者2ActionPerformed
        c.getPlayer().changeJob(1410);
    }//GEN-LAST:event_夜行者2ActionPerformed

    private void 夜行者3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_夜行者3ActionPerformed
        c.getPlayer().changeJob(1411);
    }//GEN-LAST:event_夜行者3ActionPerformed

    private void 奇袭者3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_奇袭者3ActionPerformed
        c.getPlayer().changeJob(1511);
    }//GEN-LAST:event_奇袭者3ActionPerformed

    private void 奇袭者2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_奇袭者2ActionPerformed
        c.getPlayer().changeJob(1510);
    }//GEN-LAST:event_奇袭者2ActionPerformed

    private void 奇袭者1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_奇袭者1ActionPerformed
        c.getPlayer().changeJob(1500);
    }//GEN-LAST:event_奇袭者1ActionPerformed

    private void 战神1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_战神1ActionPerformed
        c.getPlayer().changeJob(2100);
    }//GEN-LAST:event_战神1ActionPerformed

    private void 战神2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_战神2ActionPerformed
        c.getPlayer().changeJob(2110);
    }//GEN-LAST:event_战神2ActionPerformed

    private void 战神3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_战神3ActionPerformed
        c.getPlayer().changeJob(2111);
    }//GEN-LAST:event_战神3ActionPerformed

    private void 战神4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_战神4ActionPerformed
        c.getPlayer().changeJob(2112);
    }//GEN-LAST:event_战神4ActionPerformed

    private void 金币ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_金币ActionPerformed
        c.getPlayer().gainMeso(1000000, true);
    }//GEN-LAST:event_金币ActionPerformed

    private void 点券ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_点券ActionPerformed
        c.getPlayer().modifyCSPoints(1, 100000);
    }//GEN-LAST:event_点券ActionPerformed

    private void 抵用券ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_抵用券ActionPerformed
        c.getPlayer().modifyCSPoints(2, 100000);
    }//GEN-LAST:event_抵用券ActionPerformed

    private void 刷物品ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_刷物品ActionPerformed
        boolean a = this.物品代码.getText().matches("[0-9]+");
        boolean b = this.物品数量.getText().matches("[0-9]+");
        if (a && b) {
            MaplePacketCreator.getShowItemGain(Integer.parseInt(物品代码.getText()), (short) Integer.parseInt(物品数量.getText()), true);
        }
    }//GEN-LAST:event_刷物品ActionPerformed

    private void 任务完成ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_任务完成ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_任务完成ActionPerformed

    private void 刷新11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_刷新11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_刷新11ActionPerformed

    public static void main(String args[]) {
        DebugWindow.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DebugWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AP;
    private javax.swing.JButton SP;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton 主教;
    private javax.swing.JTextField 任务代码;
    private javax.swing.JButton 任务完成;
    private javax.swing.JButton 任务开始;
    private javax.swing.JButton 侠客;
    private javax.swing.JButton 侠盗;
    private javax.swing.JButton 冰雷巫师;
    private javax.swing.JButton 冰雷法师;
    private javax.swing.JButton 冰雷魔导师;
    private javax.swing.JButton 冲锋队长;
    private javax.swing.JButton 准骑士;
    private javax.swing.JButton 刷新;
    private javax.swing.JButton 刷新11;
    private javax.swing.JButton 刷物品;
    private javax.swing.JButton 刺客;
    private javax.swing.JButton 剑客;
    private javax.swing.JButton 勇士;
    private javax.swing.JButton 升级;
    private javax.swing.JButton 圣骑士;
    private javax.swing.JButton 坐标;
    private javax.swing.JButton 夜行者1;
    private javax.swing.JButton 夜行者2;
    private javax.swing.JButton 夜行者3;
    private javax.swing.JButton 大副;
    private javax.swing.JButton 奇袭者1;
    private javax.swing.JButton 奇袭者2;
    private javax.swing.JButton 奇袭者3;
    private javax.swing.JTextArea 封包;
    private javax.swing.JButton 射手;
    private javax.swing.JButton 弓箭手;
    private javax.swing.JButton 弩弓手;
    private javax.swing.JButton 战士;
    private javax.swing.JButton 战神1;
    private javax.swing.JButton 战神2;
    private javax.swing.JButton 战神3;
    private javax.swing.JButton 战神4;
    private javax.swing.JButton 抵用券;
    private javax.swing.JButton 拳手;
    private javax.swing.JButton 斗士;
    private javax.swing.JButton 无影人;
    private javax.swing.JButton 无敌;
    private javax.swing.JButton 枪战士;
    private javax.swing.JButton 海盗;
    private javax.swing.JButton 清怪;
    private javax.swing.JButton 清物;
    private javax.swing.JButton 游侠;
    private javax.swing.JButton 满属性;
    private javax.swing.JButton 满技能;
    private javax.swing.JButton 火枪手;
    private javax.swing.JButton 火毒巫师;
    private javax.swing.JButton 火毒法师;
    private javax.swing.JButton 火毒法师1;
    private javax.swing.JButton 火毒魔导师;
    private javax.swing.JButton 炎术士1;
    private javax.swing.JButton 炎术士2;
    private javax.swing.JButton 炎术士3;
    private javax.swing.JButton 点券;
    private javax.swing.JButton 牧师;
    private javax.swing.JTextField 物品代码;
    private javax.swing.JTextField 物品数量;
    private javax.swing.JButton 独行客;
    private javax.swing.JButton 神射手;
    private javax.swing.JButton 祭司;
    private javax.swing.JButton 箭神;
    private javax.swing.JButton 船长;
    private javax.swing.JButton 英雄;
    private javax.swing.JButton 金币;
    private javax.swing.JButton 隐士;
    private javax.swing.JButton 风灵使者1;
    private javax.swing.JButton 风灵使者2;
    private javax.swing.JButton 风灵使者3;
    private javax.swing.JButton 飞侠;
    private javax.swing.JButton 骑士;
    private javax.swing.JButton 魂骑士1;
    private javax.swing.JButton 魂骑士2;
    private javax.swing.JButton 魂骑士3;
    private javax.swing.JButton 魔法师;
    private javax.swing.JButton 黑骑士;
    private javax.swing.JButton 龙骑士;
    // End of variables declaration//GEN-END:variables
}
