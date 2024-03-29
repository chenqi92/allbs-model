package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static cn.allbs.utils.SFJK200.enums.UnitEnum.*;

/**
 * 枚举 GasEnum
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 16:48
 */
@Getter
public enum GasEnum {

    GAS_1(1, "输入模块", null),
    GAS_2(2, "输入输出模块", null),
    GAS_3(3, "保留3", null),
    GAS_4(4, "保留4", null),
    GAS_5(5, "保留5", null),
    GAS_6(6, "保留6", null),
    GAS_7(7, "保留7", null),
    GAS_8(8, "保留8", null),
    GAS_9(9, "保留9", null),
    GAS_10(10, "天然气", COMBUSTIBLE_GAS),
    GAS_11(11, "石油液化气", COMBUSTIBLE_GAS),
    GAS_12(12, "氢气", COMBUSTIBLE_GAS),
    GAS_13(13, "甲烷", COMBUSTIBLE_GAS),
    GAS_14(14, "乙烷", COMBUSTIBLE_GAS),
    GAS_15(15, "丙烷", COMBUSTIBLE_GAS),
    GAS_16(16, "环丙烷", COMBUSTIBLE_GAS),
    GAS_17(17, "丁烷", COMBUSTIBLE_GAS),
    GAS_18(18, "异丁烷", COMBUSTIBLE_GAS),
    GAS_19(19, "戊烷", COMBUSTIBLE_GAS),
    GAS_20(20, "己烷", COMBUSTIBLE_GAS),
    GAS_21(21, "环己烷", COMBUSTIBLE_GAS),
    GAS_22(22, "庚烷", COMBUSTIBLE_GAS),
    GAS_23(23, "辛烷", COMBUSTIBLE_GAS),
    GAS_24(24, "葵烷", COMBUSTIBLE_GAS),
    GAS_25(25, "奈烷", COMBUSTIBLE_GAS),
    GAS_26(26, "甲基环乙烷", COMBUSTIBLE_GAS),
    GAS_27(27, "甲基环己烷", COMBUSTIBLE_GAS),
    GAS_28(28, "乙基环丁烷", COMBUSTIBLE_GAS),
    GAS_29(29, "乙基环戊烷", COMBUSTIBLE_GAS),
    GAS_30(30, "乙基环己烷", COMBUSTIBLE_GAS),
    GAS_31(31, "二甲氧基乙烷", COMBUSTIBLE_GAS),
    GAS_32(32, "三甲氧基乙烷", COMBUSTIBLE_GAS),
    GAS_33(33, "硝基甲烷", COMBUSTIBLE_GAS),
    GAS_34(34, "硝基已烷", COMBUSTIBLE_GAS),
    GAS_35(35, "环氧丙烷", COMBUSTIBLE_GAS),
    GAS_36(36, "氯乙烷", COMBUSTIBLE_GAS),
    GAS_37(37, "氯丙烷", COMBUSTIBLE_GAS),
    GAS_38(38, "氯丁烷", COMBUSTIBLE_GAS),
    GAS_39(39, "乙烯", COMBUSTIBLE_GAS),
    GAS_40(40, "丙烯", COMBUSTIBLE_GAS),
    GAS_41(41, "丙二烯", COMBUSTIBLE_GAS),
    GAS_42(42, "１－丁烯", COMBUSTIBLE_GAS),
    GAS_43(43, "２－丁烯", COMBUSTIBLE_GAS),
    GAS_44(44, "丁二烯", COMBUSTIBLE_GAS),
    GAS_45(45, "异丁烯", COMBUSTIBLE_GAS),
    GAS_46(46, "环戊烯", COMBUSTIBLE_GAS),
    GAS_47(47, "苯乙烯", COMBUSTIBLE_GAS),
    GAS_48(48, "二氯乙烯", COMBUSTIBLE_GAS),
    GAS_49(49, "乙酸乙烯", TOXIC_GASES_LARGE_RANGE),
    GAS_50(50, "乙炔", COMBUSTIBLE_GAS),
    GAS_51(51, "丙炔", COMBUSTIBLE_GAS),
    GAS_52(52, "汽油", COMBUSTIBLE_GAS),
    GAS_53(53, "柴油", COMBUSTIBLE_GAS),
    GAS_54(54, "煤油", COMBUSTIBLE_GAS),
    GAS_55(55, "航空煤油", COMBUSTIBLE_GAS),
    GAS_56(56, "喷气燃油", COMBUSTIBLE_GAS),
    GAS_57(57, "石油醚", COMBUSTIBLE_GAS),
    GAS_58(58, "石脑油", COMBUSTIBLE_GAS),
    GAS_59(59, "香蕉油", COMBUSTIBLE_GAS),
    GAS_60(60, "松节油", COMBUSTIBLE_GAS),
    GAS_61(61, "四氢呋喃", COMBUSTIBLE_GAS),
    GAS_62(62, "吡啶", COMBUSTIBLE_GAS),
    GAS_63(63, "甲苯", COMBUSTIBLE_GAS),
    GAS_64(64, "二甲苯", COMBUSTIBLE_GAS),
    GAS_65(65, "乙苯", COMBUSTIBLE_GAS),
    GAS_66(66, "氯苯", COMBUSTIBLE_GAS),
    GAS_67(67, "甲基异丙基苯", COMBUSTIBLE_GAS),
    GAS_68(68, "乙醚", COMBUSTIBLE_GAS),
    GAS_69(69, "二甲醚", COMBUSTIBLE_GAS),
    GAS_70(70, "二丁醚", COMBUSTIBLE_GAS),
    GAS_71(71, "异丙醚", COMBUSTIBLE_GAS),
    GAS_72(72, "乙二醇甲醚", COMBUSTIBLE_GAS),
    GAS_73(73, "乙二醇乙醚", COMBUSTIBLE_GAS),
    GAS_74(74, "乙基甲基醚", COMBUSTIBLE_GAS),
    GAS_75(75, "二甲硫醚", COMBUSTIBLE_GAS),
    GAS_76(76, "乙硫醚", COMBUSTIBLE_GAS),
    GAS_77(77, "甲醇", COMBUSTIBLE_GAS),
    GAS_78(78, "乙醇", COMBUSTIBLE_GAS),
    GAS_79(79, "乙二醇", COMBUSTIBLE_GAS),
    GAS_80(80, "丙醇", COMBUSTIBLE_GAS),
    GAS_81(81, "异丙醇", COMBUSTIBLE_GAS),
    GAS_82(82, "丁醇", COMBUSTIBLE_GAS),
    GAS_83(83, "异丁醇", COMBUSTIBLE_GAS),
    GAS_84(84, "叔丁醇", COMBUSTIBLE_GAS),
    GAS_85(85, "戊醇", COMBUSTIBLE_GAS),
    GAS_86(86, "异戊醇", COMBUSTIBLE_GAS),
    GAS_87(87, "甲硫醇", TOXIC_GAS_SMALL_RANGE),
    GAS_88(88, "乙硫醇", COMBUSTIBLE_GAS),
    GAS_89(89, "乙醛", COMBUSTIBLE_GAS),
    GAS_90(90, "丙醛", COMBUSTIBLE_GAS),
    GAS_91(91, "丁醛", COMBUSTIBLE_GAS),
    GAS_92(92, "丙酮", COMBUSTIBLE_GAS),
    GAS_93(93, "丁酮", COMBUSTIBLE_GAS),
    GAS_94(94, "２－丁酮", COMBUSTIBLE_GAS),
    GAS_95(95, "甲乙酮", COMBUSTIBLE_GAS),
    GAS_96(96, "环已酮", COMBUSTIBLE_GAS),
    GAS_97(97, "乙酰丙酮", COMBUSTIBLE_GAS),
    GAS_98(98, "甲基异丁基酮", COMBUSTIBLE_GAS),
    GAS_99(99, "乙基甲基酮", COMBUSTIBLE_GAS),
    GAS_100(100, "丙基甲基酮", COMBUSTIBLE_GAS),
    GAS_101(101, "丁基甲基酮", COMBUSTIBLE_GAS),
    GAS_102(102, "甲酸甲脂", COMBUSTIBLE_GAS),
    GAS_103(103, "甲酸乙脂", COMBUSTIBLE_GAS),
    GAS_104(104, "乙酸甲酯", COMBUSTIBLE_GAS),
    GAS_105(105, "乙酸乙酯", COMBUSTIBLE_GAS),
    GAS_106(106, "乙酸丙脂", COMBUSTIBLE_GAS),
    GAS_107(107, "乙酸丁酯", COMBUSTIBLE_GAS),
    GAS_108(108, "乙酸戊脂", COMBUSTIBLE_GAS),
    GAS_109(109, "丁酸丁脂", COMBUSTIBLE_GAS),
    GAS_110(110, "丙烯酸甲脂", COMBUSTIBLE_GAS),
    GAS_111(111, "丙烯酸丁脂", COMBUSTIBLE_GAS),
    GAS_112(112, "甲胺", COMBUSTIBLE_GAS),
    GAS_113(113, "二甲胺", COMBUSTIBLE_GAS),
    GAS_114(114, "三甲胺", COMBUSTIBLE_GAS),
    GAS_115(115, "二甲基甲酰胺", COMBUSTIBLE_GAS),
    GAS_116(116, "三乙胺", COMBUSTIBLE_GAS),
    GAS_117(117, "正丙胺", COMBUSTIBLE_GAS),
    GAS_118(118, "异丙胺", COMBUSTIBLE_GAS),
    GAS_119(119, "环已胺", COMBUSTIBLE_GAS),
    GAS_120(120, "异辛烷", COMBUSTIBLE_GAS),
    GAS_121(121, "冰己烷", COMBUSTIBLE_GAS),
    GAS_122(122, "氢氧化钠", COMBUSTIBLE_GAS),
    GAS_123(123, "其它４", TOXIC_GASES_LARGE_RANGE),
    GAS_124(124, "其它５", TOXIC_GASES_LARGE_RANGE),
    GAS_125(125, "其它６", TOXIC_GASES_LARGE_RANGE),
    GAS_126(126, "其它７", TOXIC_GASES_LARGE_RANGE),
    GAS_127(127, "其它８", TOXIC_GASES_LARGE_RANGE),
    GAS_128(128, "人工煤气", TOXIC_GASES_LARGE_RANGE),
    GAS_129(129, "一氧化碳", TOXIC_GASES_LARGE_RANGE),
    GAS_130(130, "氧气", OXYGEN_NITROGEN),
    GAS_131(131, "臭氧", TOXIC_GAS_SMALL_RANGE),
    GAS_132(132, "氨气", TOXIC_GASES_LARGE_RANGE),
    GAS_133(133, "氯气", TOXIC_GASES_LARGE_RANGE),
    GAS_134(134, "氟气", TOXIC_GAS_SMALL_RANGE),
    GAS_135(135, "氟利昂", TOXIC_GAS_SMALL_RANGE),
    GAS_136(136, "碳酰氯", TOXIC_GAS_SMALL_RANGE),
    GAS_137(137, "乙酰氯", TOXIC_GASES_LARGE_RANGE),
    GAS_138(138, "乙晴", TOXIC_GASES_LARGE_RANGE),
    GAS_139(139, "丙烯晴", TOXIC_GASES_LARGE_RANGE),
    GAS_140(140, "氰化氯", TOXIC_GAS_SMALL_RANGE),
    GAS_141(141, "甲醛", TOXIC_GAS_SMALL_RANGE),
    GAS_142(142, "甲酸", TOXIC_GASES_LARGE_RANGE),
    GAS_143(143, "乙酸", COMBUSTIBLE_GAS),
    GAS_144(144, "硝酸", TOXIC_GASES_LARGE_RANGE),
    GAS_145(145, "氢溴酸", TOXIC_GAS_SMALL_RANGE),
    GAS_146(146, "乙胺", COMBUSTIBLE_GAS),
    GAS_147(147, "二丁胺", TOXIC_GASES_LARGE_RANGE),
    GAS_148(148, "苯胺", TOXIC_GASES_LARGE_RANGE),
    GAS_149(149, "苯酚", TOXIC_GASES_LARGE_RANGE),
    GAS_150(150, "苯", COMBUSTIBLE_GAS),
    GAS_151(151, "硝基苯", TOXIC_GASES_LARGE_RANGE),
    GAS_152(152, "硝基甲烷", TOXIC_GASES_LARGE_RANGE),
    GAS_153(153, "氯甲烷", TOXIC_GASES_LARGE_RANGE),
    GAS_154(154, "二氯甲烷", TOXIC_GASES_LARGE_RANGE),
    GAS_155(155, "三氯甲烷", TOXIC_GASES_LARGE_RANGE),
    GAS_156(156, "二氯乙烷", TOXIC_GASES_LARGE_RANGE),
    GAS_157(157, "环氧乙烷", TOXIC_GASES_LARGE_RANGE),
    GAS_158(158, "１２二氯丙烷", TOXIC_GASES_LARGE_RANGE),
    GAS_159(159, "环氧氯丙烷", TOXIC_GASES_LARGE_RANGE),
    GAS_160(160, "乙硅烷", TOXIC_GASES_LARGE_RANGE),
    GAS_161(161, "三氯硅烷", TOXIC_GASES_LARGE_RANGE),
    GAS_162(162, "乙硼烷", TOXIC_GASES_LARGE_RANGE),
    GAS_163(163, "硅烷", TOXIC_GAS_SMALL_RANGE),
    GAS_164(164, "砷烷", TOXIC_GASES_LARGE_RANGE),
    GAS_165(165, "锗烷", TOXIC_GAS_SMALL_RANGE),
    GAS_166(166, "硼烷", TOXIC_GASES_LARGE_RANGE),
    GAS_167(167, "磷烷", TOXIC_GASES_LARGE_RANGE),
    GAS_168(168, "溴乙烷", TOXIC_GAS_SMALL_RANGE),
    GAS_169(169, "溴化硼", TOXIC_GAS_SMALL_RANGE),
    GAS_170(170, "氯化硼", TOXIC_GAS_SMALL_RANGE),
    GAS_171(171, "氯化硅", TOXIC_GAS_SMALL_RANGE),
    GAS_172(172, "氯化锑", TOXIC_GAS_SMALL_RANGE),
    GAS_173(173, "氯化磷", TOXIC_GAS_SMALL_RANGE),
    GAS_174(174, "磷化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_175(175, "氯化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_176(176, "嗅化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_177(177, "氰化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_178(178, "硫化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_179(179, "氟化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_180(180, "氟化硼", TOXIC_GAS_SMALL_RANGE),
    GAS_181(181, "氟化硅", TOXIC_GAS_SMALL_RANGE),
    GAS_182(182, "氟化砷", TOXIC_GAS_SMALL_RANGE),
    GAS_183(183, "氟化氮", TOXIC_GAS_SMALL_RANGE),
    GAS_184(184, "一氧化氮", TOXIC_GAS_SMALL_RANGE),
    GAS_185(185, "二氧化氮", TOXIC_GAS_SMALL_RANGE),
    GAS_186(186, "二氧化氯", TOXIC_GAS_SMALL_RANGE),
    GAS_187(187, "二氧化碳", OXYGEN_NITROGEN),
    GAS_188(188, "二氧化硫", TOXIC_GAS_SMALL_RANGE),
    GAS_189(189, "六氟化硫", TOXIC_GAS_SMALL_RANGE),
    GAS_190(190, "四氟化碳", TOXIC_GAS_SMALL_RANGE),
    GAS_191(191, "二硫化碳", TOXIC_GASES_LARGE_RANGE),
    GAS_192(192, "氧硫化碳", TOXIC_GASES_LARGE_RANGE),
    GAS_193(193, "四氯化碳", TOXIC_GASES_LARGE_RANGE),
    GAS_194(194, "氯乙烯", COMBUSTIBLE_GAS),
    GAS_195(195, "三氯乙烯", TOXIC_GASES_LARGE_RANGE),
    GAS_196(196, "二乙烯酮", TOXIC_GASES_LARGE_RANGE),
    GAS_197(197, "氯丙酮", TOXIC_GASES_LARGE_RANGE),
    GAS_198(198, "硫酸甲酯", TOXIC_GASES_LARGE_RANGE),
    GAS_199(199, "喹啉", TOXIC_GASES_LARGE_RANGE),
    GAS_200(200, "联氨", TOXIC_GASES_LARGE_RANGE),
    GAS_201(201, "二氯环己烷", COMBUSTIBLE_GAS),
    GAS_202(202, "氮气", OXYGEN_NITROGEN),
    GAS_203(203, "砷化氢", TOXIC_GAS_SMALL_RANGE),
    GAS_204(204, "光气", TOXIC_GAS_SMALL_RANGE),
    GAS_205(205, "异辛醇", COMBUSTIBLE_GAS),
    GAS_206(206, "偏三甲苯", COMBUSTIBLE_GAS),
    GAS_207(207, "醋酸丁酯", COMBUSTIBLE_GAS),
    GAS_208(208, "醋酸正丙酯", COMBUSTIBLE_GAS),
    GAS_209(209, "醋酸正丁酯", COMBUSTIBLE_GAS),
    GAS_210(210, "醋酸异丁酯", COMBUSTIBLE_GAS),
    GAS_211(211, "醋酸异丙酯", COMBUSTIBLE_GAS),
    GAS_212(212, "四溴乙烷", TOXIC_GASES_LARGE_RANGE),
    GAS_213(213, "醋酸乙酯", COMBUSTIBLE_GAS),
    GAS_214(214, "钛酸四异丙酯", COMBUSTIBLE_GAS),
    GAS_215(215, "甲基异丁酮", COMBUSTIBLE_GAS),
    GAS_216(216, "三氟化硼", COMBUSTIBLE_GAS),
    GAS_217(217, "二乙胺", COMBUSTIBLE_GAS),
    GAS_218(218, "TDI(甲苯二异氰酸酯)", TOXIC_GASES_LARGE_RANGE),
    GAS_219(219, "二氟甲烷", COMBUSTIBLE_GAS),
    GAS_220(220, "MDI", TOXIC_GASES_LARGE_RANGE),
    GAS_221(221, "正丁醇", COMBUSTIBLE_GAS),
    GAS_222(222, "醋酸丙酯", COMBUSTIBLE_GAS),
    GAS_223(223, "PM", COMBUSTIBLE_GAS),
    GAS_224(224, "PMA", COMBUSTIBLE_GAS),
    GAS_225(225, "丁酯", COMBUSTIBLE_GAS),
    GAS_226(226, "乙酯", COMBUSTIBLE_GAS),
    GAS_227(227, "异甲醚", COMBUSTIBLE_GAS),
    GAS_228(228, "MMA", COMBUSTIBLE_GAS),
    GAS_229(229, "氯气", TOXIC_GAS_SMALL_RANGE),
    GAS_230(230, "氨气", TOXIC_GAS_SMALL_RANGE),
    GAS_231(231, "苯", TOXIC_GAS_SMALL_RANGE),
    GAS_232(232, "溴甲烷", TOXIC_GAS_SMALL_RANGE),
    GAS_233(233, "环氧乙烷", TOXIC_GAS_SMALL_RANGE),
    GAS_234(234, "四氢噻吩", TOXIC_GAS_SMALL_RANGE),
    GAS_235(235, "溴气", TOXIC_GAS_SMALL_RANGE),
    GAS_236(236, "其它45", TOXIC_GASES_LARGE_RANGE),
    GAS_237(237, "其它46", TOXIC_GASES_LARGE_RANGE),
    GAS_238(238, "其它47", TOXIC_GASES_LARGE_RANGE),
    GAS_239(239, "其它48", TOXIC_GASES_LARGE_RANGE),
    GAS_240(240, "其它49", TOXIC_GASES_LARGE_RANGE),
    GAS_241(241, "其它50", TOXIC_GASES_LARGE_RANGE),
    GAS_242(242, "其它51", TOXIC_GASES_LARGE_RANGE),
    GAS_243(243, "其它52", TOXIC_GASES_LARGE_RANGE),
    GAS_244(244, "其它53", TOXIC_GASES_LARGE_RANGE),
    GAS_245(245, "其它54", TOXIC_GASES_LARGE_RANGE),
    GAS_246(246, "其它55", TOXIC_GASES_LARGE_RANGE),
    GAS_247(247, "其它56", TOXIC_GASES_LARGE_RANGE),
    GAS_248(248, "其它57", TOXIC_GASES_LARGE_RANGE),
    GAS_249(249, "其它58", TOXIC_GASES_LARGE_RANGE),
    GAS_250(250, "其它59", TOXIC_GASES_LARGE_RANGE),
    GAS_251(251, "特殊气种", TOXIC_GASES_LARGE_RANGE),
    GAS_252(252, "特殊气种", COMBUSTIBLE_GAS),
    GAS_253(253, "特殊气种", OXYGEN_NITROGEN),
    GAS_254(254, "特殊气种", TOXIC_GAS_SMALL_RANGE),
    ;

    public static final Map<Integer, GasEnum> GAS_ENUM_MAP = new HashMap<>(254);

    static {
        GasEnum[] gasEnums = GasEnum.values();
        for (GasEnum gas : gasEnums) {
            GAS_ENUM_MAP.put(gas.type, gas);
        }
    }

    private final Integer type;

    private final String desc;

    private final UnitEnum unit;

    GasEnum(Integer type, String desc, UnitEnum unit) {
        this.type = type;
        this.desc = desc;
        this.unit = unit;
    }

}
