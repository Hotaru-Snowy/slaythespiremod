package asimplemodforsts;

/**
 * 资源地址
 */
public class ResourceLib {
    public static final String CARDIMGPATH = "images/cards/";
    public static final String RELICIMGPATH = "images/relics/";
    public static final String CHARAIMGPATH = "images/characters/";

    /**
     * 根据卡牌ID获得图片
     * @param id 卡牌ID
     * @return 图片地址
     */
    public static String cardImagePath(String id) {
        return CARDIMGPATH+id+".png";
    }
    /**
     * 根据遗物ID获得图片
     * @param id 遗物ID
     * @return 图片地址
     */
    public static String relicImagePath(String id) {
        return RELICIMGPATH+id+".png";
    }

    /**
     * 根据文件名获得目标语言的目标文件
     * @param lang 目标语言
     * @param tag  目标文件
     * @return 语言文件地址
     */
    public static String langFilePath(String lang,String tag) {
        return "lang/"+lang+"/"+tag+".json";
    }

    /**
     * 根据文件名获得中文的目标语言文件
     * @param tag 目标文件
     * @return 语言文件地址
     */
    public static String langFilePath(String tag) {
        return langFilePath("zhs",tag);
    }

    /**
     * 根据人物ID和目标图片名获得图片地址
     * @param chara 角色名
     * @param tag 目标图片
     * @return 图片地址
     */
    public static String charaImagePath(String chara,String tag){
        return "images/characters/"+chara+"/"+tag+".png";
    }

    /**
     * 根据目标图片名获得丽芙的图片地址
     * @param tag 目标图片
     * @return 图片地址
     */
    public static String livImagePath(String tag){
        return charaImagePath("Liv",tag);
    }
    /**
     * 根据BUFF ID获得图片
     * @param id BUFF ID
     * @return 图片地址
     */
    public static String powerImagePath(String id){
        return "images/powers/"+id+".png";
    }
    /**
     * 根据BUFF ID获得图片
     * @param id BUFF ID
     * @return 图片地址
     */
    public static String powerImageLargePath(String id){
        return "images/powers/"+id+"_LARGE.png";
    }
}
