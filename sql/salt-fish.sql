/*
 Navicat Premium Data Transfer

 Source Server         : luna-utools
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : cn1.utools.club:42268
 Source Schema         : salt-fish

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 05/12/2020 23:26:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `goods_id` int(11) NOT NULL COMMENT '收集物品编号',
  PRIMARY KEY (`collect_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of collect
-- ----------------------------
BEGIN;
INSERT INTO `collect` VALUES (2, 1022, 15);
INSERT INTO `collect` VALUES (3, 0, 7);
INSERT INTO `collect` VALUES (4, 0, 1);
INSERT INTO `collect` VALUES (5, 1022, 2);
INSERT INTO `collect` VALUES (6, 1022, 3);
INSERT INTO `collect` VALUES (7, 123, 2);
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL,
  `image` char(255) COLLATE utf8_bin NOT NULL,
  `type_id` int(11) NOT NULL COMMENT '类型id',
  `name` char(255) COLLATE utf8_bin NOT NULL COMMENT '商品名',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `price` float NOT NULL,
  `status` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  `producter_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (1, 'static/goods_img/1.jpg', 4, '笔记本', 1, 4000, 2, '二手笔记本，8成新，I7处理器', 10, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (2, 'static/goods_img/2.jpg', 2, '被套', 1, 30, 4, '二手被套', 1017, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (3, 'static/goods_img/3.jpg', 2, '自行车', 1, 50, 2, '二手自行车', 1017, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (4, 'static/goods_img/4.jpg', 5, '网球拍', 1, 50, 2, '二手网球拍，用过几天，九成新', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (5, 'static/goods_img/5.jpg', 5, '篮球', 1, 80, 2, '全牛皮篮球，', 9, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (6, 'static/goods_img/6.jpg', 2, '懒人桌', 1, 15, 2, '加固型懒人桌，九成新', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (7, 'static/goods_img/7.jpg', 1, '考研书', 1, 30, 2, '聚星文登考研', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (8, 'static/goods_img/8.jpg', 1, '公务员书', 1, 30, 2, '公务员考试书籍，9成新', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (9, 'static/goods_img/9.jpg', 2, '凉席', 1, 60, 2, '寝室牛皮凉席', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (10, 'static/goods_img/10.jpg', 2, '纯棉枕头', 1, 50, 2, '纯棉枕头', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (11, 'static/goods_img/11.jpg', 2, 'LED台灯，学习卧室床头书桌大学生寝室插电节能USB调光夹子台灯', 1, 100, 2, '灯光颜色默认自然光，轻微偏黄的灯光颜色，台灯默认USB接口，台灯供电方式：1，可用一切手机充电器直接插220V家用插座。 2，可接电脑USB。 3，可接手机充电宝供电。（注：这款不是充电台灯，不带蓄电池，必须连着电源使用）', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (12, 'static/goods_img/12.jpg', 1, '《c primer plus(第五版)中文版》C语言经典入门书籍', 1, 23, 2, '只翻过几次，几乎全新。', 10, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (13, 'static/goods_img/13.jpg', 4, '诺基亚830手机', 1, 1200, 2, '购买时间在一年内，无保修，屏幕无划痕或坏点，机身有破裂损伤，完全正常，曾无拆无修，待机时长超过2天。相关配件有原装电池。', 10, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (14, 'static/goods_img/14.jpg', 2, '室内物品收纳架，多功能免钉可伸缩衣柜分层隔板', 1, 11, 2, '多功能免钉无痕衣柜分层架，', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (15, 'static/goods_img/15.jpg', 3, '沃曼威斯韩版夜光双肩包大容量个性背包', 1, 50, 2, '书包，8成新\r\n', 123, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (16, 'static/goods_img/16.jpg', 4, '华为荣耀4x手机', 1, 450, 2, '移动4g标配版在保九新，京东抢购的，配件发票箱盒齐全，已经贴好钢化膜，送一软壳，便框有些许磕碰，不明显，屏幕右上方有出厂黄斑，4x通病，买回来就这样，无拆无修，特价处理。不议价，顺丰到付。', 10, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (17, 'static/goods_img/17.jpg', 4, '畅学STM32开发学习板，配套stm32f103c8t6最小系统核心板', 1, 67, 2, '畅学STM32开发学习板，所有模块均可用', 10, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (18, 'static/goods_img/18.jpg', 1, '地球往事系列小说 ，三体1+三体2黑暗森林+三体3死神永生', 1, 72, 2, '重庆出版集团出版\r\n全部是正版\r\nISBN编号: 9787536693968', 10, '2020-12-24 15:43:54');
INSERT INTO `goods` VALUES (19, 'static/goods_img/19.jpg', 1, '《1984》(精装珍藏本) 奥威尔著  世界名著小说', 1, 23, 2, '全新\r\n中国画报出版社出版\r\n译者: 林东泰\r\nISBN编号: 9787514601312\r\n2011年08月', 10, '2020-12-25 15:57:39');
INSERT INTO `goods` VALUES (20, 'static/goods_img/20.jpg', 4, '二手小新2018air15', 1, 3000, 3, 'i5 8代处理器 预装win10 与macos11', 123, '2020-12-05 23:11:55');
INSERT INTO `goods` VALUES (21, 'static/goods_img/21.jpg', 4, '二手小新2018air15', 1, 3000, 3, '二手小新2018air15', 123, '2020-12-05 23:13:24');
INSERT INTO `goods` VALUES (22, 'static/goods_img/22.jpg', 6, '保温杯', 1, 30, 2, '是原来SM-KB48的升级版。瓶身体积小，便于携带，可单手饮水。采用304真空不锈钢材质内胆，68摄氏度以上保温6小时，9摄氏度以下保冷6小时。内胆防沾涂层、可拆卸中栓，便于清洗。One-Touch一键开合方便饮用，并且带LOCK锁定按钮，防止意外开启。结露抑制技术能在温差大的情况下，减少瓶身外盖结露。规格480ml容量。', 1025, '2020-12-05 23:22:05');
INSERT INTO `goods` VALUES (23, 'static/goods_img/23.jpg', 1, '二手小新2018air15', 1, 3000, 2, '预装win10 与macos 11.1 系统 完美黑苹果', 123, '2020-12-05 23:22:59');
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mess_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `mess_from_id` int(11) NOT NULL COMMENT '消息接收者',
  `mess_to_id` int(11) NOT NULL COMMENT '消息发布者',
  `mess_text` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '消息内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `mess_type` int(11) DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`mess_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
INSERT INTO `message` VALUES (49, 1, 10, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=17\'>畅学STM32开发学习板，配套stm32f103c8t6最小系统核心板</a>已经审核通过', '2020-12-05 23:01:31', NULL);
INSERT INTO `message` VALUES (50, 1, 10, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=18\'>地球往事系列小说 ，三体1+三体2黑暗森林+三体3死神永生</a>已经审核通过', '2020-12-05 23:01:31', NULL);
INSERT INTO `message` VALUES (51, 1, 10, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=19\'>《1984》(精装珍藏本) 奥威尔著  世界名著小说</a>已经审核通过', '2020-12-05 23:01:32', NULL);
INSERT INTO `message` VALUES (52, 123, 1017, '你的商品<a target=\'_blank\' href=\'goods/info.jsp?goodsid=2\'>被套</a>被购买，请尽快联系买家<a target=\'_blank\' href=\'user/personal.jsp?tab=mess&handle=write&toemail=2236188843@qq.com ==> 王华强&userId=123\'>王华强</a>，以下为买家的附加信息（可能为空）\n==============\n你好 价格比较合适 期望与您联系', '2020-12-05 23:02:22', NULL);
INSERT INTO `message` VALUES (53, 1017, 123, '好的  欢迎与您取得联系 我的电话156xxxxx', '2020-12-05 23:06:25', NULL);
INSERT INTO `message` VALUES (54, 123, 1017, '好的呢  我会与您联系的', '2020-12-05 23:08:20', NULL);
INSERT INTO `message` VALUES (55, 1, 123, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=20\'>二手小新2018air15</a>审核未通过', '2020-12-05 23:21:53', NULL);
INSERT INTO `message` VALUES (56, 1, 123, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=21\'>二手小新2018air15</a>审核未通过', '2020-12-05 23:21:58', NULL);
INSERT INTO `message` VALUES (57, 1, 1025, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=22\'>保温杯</a>已经审核通过', '2020-12-05 23:23:30', NULL);
INSERT INTO `message` VALUES (58, 1, 123, '你的商品 <a target=\'_blank\' href=\'goods/info.jsp?goodsid=23\'>二手小新2018air15</a>已经审核通过', '2020-12-05 23:23:33', NULL);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL COMMENT '订单物品编号',
  `user_id` int(11) NOT NULL COMMENT '购买用户',
  `date` datetime NOT NULL COMMENT '订单日期',
  `message` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单留言',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (8, 4, 1017, '2020-12-05 22:56:47', '你好 真的很喜欢');
INSERT INTO `order` VALUES (9, 2, 123, '2020-12-05 23:02:22', '你好 价格比较合适 期望与您联系');
COMMIT;

-- ----------------------------
-- Table structure for sessionkey
-- ----------------------------
DROP TABLE IF EXISTS `sessionkey`;
CREATE TABLE `sessionkey` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `session_key` varchar(127) CHARACTER SET utf8 NOT NULL,
  `user_id` int(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sessionkey
-- ----------------------------
BEGIN;
INSERT INTO `sessionkey` VALUES (3, 'c64102dbe0be79b76d9113086591d5f5', 0);
INSERT INTO `sessionkey` VALUES (4, '7bec8fc80405741cb40f017fad543ddc', 1022);
INSERT INTO `sessionkey` VALUES (5, '531e178ba446ac20ad61783af55cdf28', 1017);
INSERT INTO `sessionkey` VALUES (6, '6b568b6071c605f3315d0291ef20ca19', 1024);
INSERT INTO `sessionkey` VALUES (7, '35de170fc7836ea645e1a7d7b307ff6e', 1025);
INSERT INTO `sessionkey` VALUES (8, '211af61ce87c84a71bca7c42a14b56b4', 123);
COMMIT;

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
BEGIN;
INSERT INTO `shoppingcart` VALUES (38, 3, 1025);
INSERT INTO `shoppingcart` VALUES (39, 4, 1025);
INSERT INTO `shoppingcart` VALUES (40, 9, 1025);
INSERT INTO `shoppingcart` VALUES (41, 2, 123);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` char(255) COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `img` char(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `pwd` char(255) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` char(255) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `stu_num` char(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学号',
  `qq` char(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号',
  `phone` char(255) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `mess_num` int(11) NOT NULL DEFAULT '0' COMMENT '消息数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (0, 'luna@saltfish.com', 'static/user_img/0.jpg', '385895e41f65a63bdfb93b9d2048e69d', 'luna', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1, 'luna@saltfish.com', 'static/user_img/0.jpg', '385895e41f65a63bdfb93b9d2048e69d', 'luna', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (9, '1173282254@qq.com', 'static/user_img/9.jpg', '385895e41f65a63bdfb93b9d2048e69d', '陈章月', NULL, NULL, '', 0);
INSERT INTO `user` VALUES (10, '1159891180@qq.com', 'static/user_img/1.jpg', '385895e41f65a63bdfb93b9d2048e69d', '韩志强', NULL, NULL, '15256925578', 6);
INSERT INTO `user` VALUES (123, '2236188843@qq.com', 'static/user_img/1022.jpg', '385895e41f65a63bdfb93b9d2048e69d', '王华强', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1017, '865616284@qq.com', 'static/user_img/1017.jpg', '385895e41f65a63bdfb93b9d2048e69d', '赵文军', NULL, NULL, '13245634567', 1);
INSERT INTO `user` VALUES (1019, '864636142@qq.com', 'static/user_img/1019.jpg', '385895e41f65a63bdfb93b9d2048e69d', '罗杰', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1020, '121@qq.com', 'static/user_img/1022.jpg', '385895e41f65a63bdfb93b9d2048e69d', NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1021, 'leilei@qq.com', 'static/user_img/1022.jpg', '385895e41f65a63bdfb93b9d2048e69d', NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1022, 'luna_nov@163.com', 'static/user_img/1022.jpg', '385895e41f65a63bdfb93b9d2048e69d', 'luna', NULL, NULL, '15696756582', 0);
INSERT INTO `user` VALUES (1023, 'xiaoming@saltfish.com', 'static/user_img/1022.jpg', '385895e41f65a63bdfb93b9d2048e69d', '小明', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1024, '865616285@qq.com', NULL, '385895e41f65a63bdfb93b9d2048e69d', '小红', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1025, '123456@qq.com', NULL, '14e1b600b1fd579f47433b88e8d85291', '陈章月', NULL, NULL, NULL, 1);
COMMIT;

-- ----------------------------
-- Triggers structure for table goods
-- ----------------------------
DROP TRIGGER IF EXISTS `notify_auditing`;
delimiter ;;
CREATE TRIGGER `notify_auditing` AFTER UPDATE ON `goods` FOR EACH ROW begin
set @goodsid=new.id;
set @goodsname=new.name;
set @newst=new.status;
set @oldst=old.status;
if ((@newst=2) and (@oldst=1)) then
INSERT INTO `message`(mess_from_id,mess_to_id,mess_text,send_time) VALUES(1,new.producter_id,CONCAT("你的商品 ","<a target='_blank' href='goods/info.jsp?goodsid=",@goodsid,"'>",@goodsname,"</a>","已经审核通过"),now());
end if;
if ((@newst=3) and (@oldst=1)) then
INSERT INTO `message`(mess_from_id,mess_to_id,mess_text,send_time) VALUES(1,new.producter_id,CONCAT("你的商品 ","<a target='_blank' href='goods/info.jsp?goodsid=",@goodsid,"'>",@goodsname,"</a>","审核未通过"),now());
end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table message
-- ----------------------------
DROP TRIGGER IF EXISTS `change_user_mess_num`;
delimiter ;;
CREATE TRIGGER `change_user_mess_num` AFTER INSERT ON `message` FOR EACH ROW update user set mess_num=mess_num+1 where id=new.mess_to_id
;
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table order
-- ----------------------------
DROP TRIGGER IF EXISTS `update_goods_status`;
delimiter ;;
CREATE TRIGGER `update_goods_status` AFTER INSERT ON `order` FOR EACH ROW begin
set @buyerid=new.user_id;
set @buyername=(select name from user where id=@buyerid);
set @buyerEmail=(select email from user where id=@buyerid);
set @sellerid = (select producter_id from goods where id=new.goods_id);
set @goodsid = (select id from goods where id=new.goods_id);
set @goodsname = (select name from goods where id=new.goods_id);
set @sellername = (select name from `user` where id=@sellerid);
INSERT INTO `message`(mess_from_id,mess_to_id,mess_text,send_time) VALUES (@buyerid,@sellerid,CONCAT("你的商品","<a target='_blank' href='goods/info.jsp?goodsid=",@goodsid,"'>",@goodsname,"</a>","被购买，请尽快联系买家","<a target='_blank' href='user/personal.jsp?tab=mess&handle=write&toemail=",@buyerEmail,"%20==>%20",@buyername,"&userId=",@buyerid,"'>",@buyername,"</a>","，以下为买家的附加信息（可能为空）
==============
",new.message),new.date);
update goods set status=4 where id=new.goods_id;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
