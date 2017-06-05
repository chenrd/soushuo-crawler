/*
 * 文件名：BookType.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年4月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.jyue.crawler.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 书本类型类别
 * @author chenrd
 * @version 2017年4月19日
 * @see BookType
 * @since
 */
public enum BookType {
	
	// =============== 玄幻奇幻 ==============
	mo_fa_xiao_yuan(1, 1, "魔法校园"), yi_jie_da_lu(1, 2, "异界大陆"), ling_zhu_gui_zu(1, 3, "领主贵族"), yi_shu_chao_neng(1, 4, "异术超能"), 
	xi_fang_qi_huan(1, 5, "西方奇幻"), yi_shi_zhen_ba(1, 6, "异世争霸"), dong_fang_xuan_huan(1, 7, "东方玄幻"),
	
	// ============== 仙侠武侠 ============
	xi_dai_yi_xia(2, 1, "现代异侠"), xian_dai_xiu_zhen(2, 2, "现代修真"), li_shi_wu_xia(2, 3, "历史武侠"), qi_huan_xiu_zhen(2, 4, "奇幻修真"),
	chuan_tong_wu_xia(2, 5, "传统武侠"), guo_shu_gu_wu(2, 6, "国术古武"), hong_huan_feng_shen(2, 7, "洪荒封神"), du_dian_xian_xia(2, 8, "古典仙侠"),
	
	// ============== 都市小说 ============
	du_shi_ji_qing(3, 1, "都市激战"), du_shi_chong_sheng(3, 2, "都市重生"), du_shi_sheng_huo(3, 3, "都市生活"), xiang_cun_xian_tu(3, 4, "乡村乡土"), 
	yu_le_ming_xian(3, 5, "娱乐明星"), zhi_chang_li_zhi(3, 6, "职场励志"), re_xue_qing_chun(3, 7, "热血青春"), shang_ye_da_heng(3, 8, "商业大亨"), 
	guan_chang_feng_yun(3, 9, "官场风云"), du_shi_yi_neng(3, 10, "都市异能"), xiao_yuan_feng_yun(3, 11, "校园风云"),
	
	// ============== 历史竞技 =============
	li_shi_chuan_qi(4, 1, "历史传奇"), zhan_zheng_huan_xiang(4, 2, "战争幻想"), zhan_shi_feng_yun(4, 3, "战史风云"), jia_kong_li_shi(4, 4, "架空历史"),
	die_zhan_te_gong(4, 5, "谍战特工"), jun_lv_sheng_ya(4, 6, "军旅生涯"), li_shi_chuan_yue(4, 7, "历史穿越"),
	
	// ============== 游戏竞技 =============
	you_xi_yi_jie(5, 1, "游戏异界"), tian_xia_zu_qiu(5, 2, "天下足球"), dian_zi_jing_ji(5, 3, "电子竞技"), qi_pai_zhuo_you(5, 4, "棋牌桌游"),
	you_xi_sheng_ya(5, 5, "游戏生涯"), ti_yu_jing_ji(5, 6, "体育竞技"), lan_qiu_feng_yun(5, 7, "篮球风云"), xu_ni_wang_you(5, 8, "虚拟网游"),
	
	//=============== 科幻末世 ==================
	gu_wu_ji_jia(6, 1, "古武机甲"), mo_shi_wei_ji(6, 2, "末世危机"), jin_hua_bian_yi(6, 3, "进化变异"), wei_lai_huan_xiang(6, 4, "未来幻想"),
	shi_kong_chuan_yue(6, 5, "时空穿梭"), xing_ji_zhan_zheng(6, 6, "星际战争");
	
	public int index;
	
	public String name;
	
	/**
	 * 两层的类型，顶层
	 * @param topType 
	 * @param type
	 * @param name
	 */
	private BookType(int topType, int type, String name) {
		this.index = topType << 8 | type;
		this.name = name;
		BookMap.types.put(name, this);
	}
	
	public static int getTypeIndex(String name) {
		if (BookMap.types.get(name) == null) {
			return 0;
		}
		return BookMap.types.get(name).index;
	}
	
}

class BookMap {
	static Map<String, BookType> types = new HashMap<String, BookType>();
}