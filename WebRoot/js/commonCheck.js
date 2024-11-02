
//字母と数字。
var englishNumberText = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZａｂｃｄｅｆ" +
                        "ｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" +
                        "1234567890１２３４５６７８９０" + "　 ，,。？！‘';()=:+-_%/><.、……@#￥……&×*}{@[]" + '"';
//平仮名と片仮名。
var kana =  "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
	        "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
			"ぁァぃィぅゥぇェぉォヵヶっッゃャゅュょョ－ー-―‐" +
            "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ";
//日本語
var s1 ="娜磊一右雨円王音下火花貝学気九休玉金空月犬見五口校左三山子四糸字耳倩冨富杯賣売" +
        "小上森人水正生青夕石赤千川先早草足村大男竹中虫町天田土二日入年白八百文木本名目立力林六涛坤捗樣様" +
        "謠薬與与搖揺引羽雲園遠何科夏家歌画回会海絵外角楽活間丸岩顔汽記帰弓牛魚京強教近兄形計元言原戸古午" +
        "後語工公広交光考行高黄合谷国黒今才細作算止市矢姉思紙寺自時室社弱首秋週春書少場色食心新親図数西声" +
        "星晴切雪船線前組走多太体台地池知茶昼長鳥朝直通弟店点電刀冬当東答頭同道読内南肉馬売買麦半番父風分" +
        "聞米歩母方北毎妹万明鳴毛門夜野友用曜来里理話悪安暗医委意育員院飲運泳駅央横屋温化荷界開階寒感漢" +
        "館岸起期客究急級宮球去橋業曲局銀区苦具君係軽血決研県庫湖向幸港号根祭皿仕死使始指歯詩次事持式実写" +
        "者主守取酒受州拾終習集住重宿所暑助昭消商章勝乗植申身神真深進世整昔全相送想息速族他打対待代第題炭" +
        "短談着注柱丁帳調追定庭笛鉄転都度投豆島湯登等動童農波配倍箱畑発反坂板皮悲美鼻筆氷表秒病品負部服福" +
        "物平返勉放味命面問役薬由油有遊予羊洋葉陽様落流旅両緑礼列練路和謡來来?頼覽覧欄龍竜虜凉涼?緑涙愛案" +
        "以衣位囲胃印英栄塩億加果貨課芽改械害街各覚完官管関観願希季紀喜旗器機議求泣救給挙漁共協鏡競極訓軍" +
        "郡径型景芸欠結建健験固功好候航康告差菜最材昨札刷殺察参産散残士氏史司試児治辞失借種周祝順初松笑唱" +
        "焼象照賞臣信成省清静席積折節説浅戦選然争倉巣束側続卒孫帯隊達単置仲貯兆腸低底停的典伝徒努灯堂働特" +
        "得毒熱念敗梅博飯飛費必票標不夫付府副粉兵別辺変便包法望牧末満未脈民無約勇要養浴利陸良料量輪類令冷" +
        "例歴連老労録侮福拂払佛仏勉歩峯峰墨飜翻毎萬万圧移因永営衛易益液演応往桜恩可仮価河過賀快解格確額刊" +
        "幹慣眼基寄規技義逆久旧居許境均禁句群経潔件券険検限現減故個護効厚耕鉱構興講混査再災妻採際在財罪雑" +
        "酸賛支志枝師資飼示似識質舎謝授修述術準序招承証条状常情織職制性政勢精製責績接設舌絶銭祖素総造像増" +
        "則測属率損退貸態団断築張提程適敵統銅導徳独任燃能破犯判版比肥非備俵評貧布婦富武復複仏編弁保墓報豊" +
        "防貿暴務夢迷綿輸余預容略留枚幕密盟模訳郵優幼欲翌乱卵覧裏律臨朗論默黙埜野藥抜繁晩卑祕秘碑賓敏" +
        "異遺域宇映延沿我灰拡革閣割株干巻看簡危机揮貴疑吸供胸郷勤筋系敬警劇激穴絹権憲源厳己呼誤后孝皇紅降" +
        "鋼刻穀骨困砂座済裁策冊蚕至私姿視詞誌磁射捨尺若樹収宗就衆従縦縮熟純処署諸除将傷障城蒸針仁垂推寸盛" +
        "聖誠宣専泉洗染善奏窓創装韻右宇羽雨渦沖億屋憶乙卸悔懐戒拐改械株刈乾冠寒刊企危喜器基奇丘久休及吸宮" +
        "層操蔵臓存尊宅担探誕段暖値宙忠著庁頂潮賃痛展討党糖届難乳認納脳派拝背肺俳班晩否批秘腹奮並陛閉片補" +
        "暮宝訪亡忘棒郷鏡響驚仰凝啓型契形径恵県肩見謙賢軒功効厚口向后号合拷豪克刻載際剤在材罪市師志思指支" +
        "亜哀愛悪握圧扱安暗案以位依偉囲委威尉意慰易為異移維緯胃衣違遺医井域育一壱逸稲芋印員因姻引飲院陰隠" +
        "浦運雲営影映栄永泳英衛詠鋭液疫益駅悦謁越閲円園宴延援沿演炎煙猿縁遠鉛塩汚凹央奥往応押横欧殴王翁黄" +
        "恩温穏音下化仮何価佳加可夏嫁家寡科暇果架歌河火禍稼箇花荷華菓課貨過蚊我画芽賀雅餓介会解回塊壊快怪" +
        "海灰界皆絵開階貝劾外害慨概涯街該垣嚇各拡格核殻獲確穫覚角較郭閣隔革学岳楽額掛潟割喝括活渇滑褐轄且" +
        "勘勧巻喚堪完官寛干幹患感慣憾換敢棺款歓汗漢環甘監看管簡緩缶肝艦観貫還鑑間閑関陥館丸含岸眼岩頑顔願" +
        "寄岐希幾忌揮机旗既期棋棄機帰気汽祈季紀規記貴起軌輝飢騎鬼偽儀宜戯技擬欺犠疑義議菊吉喫詰却客脚虐逆" +
        "弓急救朽求泣球究窮級糾給旧牛去居巨拒拠挙虚許距漁魚享京供競共凶協叫境峡強恐恭挟教橋況狂狭矯胸脅興" +
        "暁業局曲極玉勤均斤琴禁筋緊菌襟謹近金吟銀九句区苦駆具愚虞空偶遇隅屈掘靴繰桑勲君薫訓群軍郡係傾刑兄" +
        "慶憩掲携敬景渓系経継茎蛍計警軽鶏芸迎鯨劇撃激傑欠決潔穴結血月件倹健兼券剣圏堅嫌建憲懸検権犬献研絹" +
        "遣険顕験元原厳幻弦減源玄現言限個古呼固孤己庫弧戸故枯湖誇雇顧鼓五互午呉娯後御悟碁語誤護交侯候光公" +
        "坑好孔孝工巧幸広康恒慌抗拘控攻更校構江洪港溝甲皇硬稿紅絞綱耕考肯航荒行衡講貢購郊酵鉱鋼降項香高剛" +
        "告国穀酷黒獄腰骨込今困墾婚恨懇昆根混紺魂佐唆左差査砂詐鎖座債催再最妻宰彩才採栽歳済災砕祭斎細菜裁" +
        "財坂咲崎作削搾昨策索錯桜冊刷察撮擦札殺雑皿三傘参山惨散桟産算蚕賛酸暫残仕伺使刺司史嗣四士始姉姿子" +
        "施旨枝止死氏祉私糸紙紫肢脂至視詞詩試誌諮資賜雌飼歯事似侍児字寺慈持時次滋治璽磁示耳自辞式識軸七執" +
        "失室湿漆疾質州修愁拾秀秋所暑庶緒署書衝訟証詔詳象神紳臣薪親診星晴正清牲生栓泉浅洗染潜相窓総草荘葬" +
        "実芝舎写射捨赦斜煮社者謝車遮蛇邪借勺尺爵酌釈若寂弱主取守手朱殊狩珠種趣酒首儒受寿授樹需囚収周宗就" +
        "終習臭舟衆襲週酬集醜住充十従柔汁渋獣縦重銃叔宿淑祝縮粛塾熟出術述俊春瞬准循旬殉準潤盾純巡遵順処初" +
        "諸助叙女序徐除傷償勝匠升召商唱奨宵将小少尚床彰承抄招掌昇昭晶松沼消渉焼焦照症省硝礁祥称章笑粧紹肖" +
        "賞鐘障上丈乗冗剰城場壌嬢常情条浄状畳蒸譲醸錠嘱飾植殖織職色触食辱伸信侵唇娠寝審心慎振新森浸深申真" +
        "身辛進針震人仁刃尋甚尽迅陣酢図吹垂帥推水炊睡粋衰遂酔錘随髄崇数枢据杉澄寸世瀬畝是制勢姓征性成政整" +
        "盛精聖声製西誠誓請逝青静斉税隻席惜斥昔析石積籍績責赤跡切拙接摂折設窃節説雪絶舌仙先千占宣専川戦扇" +
        "旋線繊船薦践選遷銭銑鮮前善漸然全禅繕塑措疎礎祖租粗素組訴阻僧創双倉喪壮奏層想捜掃挿操早曹巣槽燥争" +
        "藻装走送遭霜騒像増憎臓蔵贈造促側則即息束測足速俗属賊族続卒存孫尊損村他多太堕妥惰打駄体対耐帯待怠" +
        "態替泰滞胎袋恥池痴稚置致勅直朕沈珍賃添転点伝殿田闘働動同堂導濃納能脳農把判半反帆搬板標氷漂票表評" +
        "貸退逮隊代台大第題滝卓宅択拓沢濯託濁諾但達奪脱棚谷丹単嘆担探淡炭短端胆誕鍛団壇弾断暖段男談値知地" +
        "遅築畜竹蓄逐秩窒茶嫡着中仲宙忠抽昼柱注虫衷鋳駐著貯丁兆帳庁弔張彫徴懲挑朝潮町眺聴脹腸調超跳長頂鳥" +
        "鎮陳津墜追痛通塚漬坪釣亭低停偵貞呈堤定帝底庭廷弟抵提程締艇訂逓邸泥摘敵滴的笛適哲徹撤迭鉄典天展店" +
        "電吐塗徒斗渡登途都努度土奴怒倒党冬凍刀唐塔島悼投搭東桃棟盗湯灯当痘等答筒糖統到討謄豆踏逃透陶頭騰" +
        "洞童胴道銅峠匿得徳特督篤毒独読凸突届屯豚曇鈍内縄南軟難二尼弐肉日乳入如尿任妊忍認寧猫熱年念燃粘悩" +
        "覇波派破婆馬俳廃拝排敗杯背肺輩配倍培媒梅買売賠陪伯博拍泊白舶薄迫漠爆縛麦箱肌畑八鉢発髪伐罰抜閥伴" +
        "版犯班畔繁般藩販範煩頒飯晩番盤蛮卑否妃彼悲扉批披比泌疲皮碑秘罷肥被費避非飛備尾微美鼻匹必筆姫百俵" +
        "描病秒苗品浜貧賓頻敏瓶不付夫婦富布府怖扶敷普浮父符腐膚譜負賦赴附侮武舞部封風伏副復幅服福腹複覆払" +
        "沸仏物分噴墳奉宝峰崩抱放魔麻埋妹枚毎戻問紋門匁夜羊葉要謡踊陽療糧良量陵領和話賄惑枠湾凜凧凪凰凱函" +
        "憤奮粉紛雰文聞丙併兵塀幣平弊柄並閉陛米壁癖別偏変片編辺返遍便勉弁保舗捕歩補穂募墓慕暮母簿倣俸包報" +
        "方法泡砲縫胞芳褒訪豊邦飽乏亡傍剖坊妨帽忘忙房暴望某棒冒紡肪膨謀貿防北僕墨撲朴牧没堀奔本翻凡盆摩磨" +
        "幕膜又抹末繭万慢満漫味未魅岬密脈妙民眠務夢無矛霧婿娘名命明盟迷銘鳴滅免綿面模茂妄毛猛盲網耗木黙目" +
        "野矢厄役約薬訳躍柳愉油癒諭輸唯優勇友幽悠憂有猶由裕誘遊郵雄融夕予余与誉預幼容庸揚揺擁曜様洋溶用窯" +
        "養抑欲浴翌翼羅裸来頼雷絡落酪乱卵欄濫覧利吏履理痢裏里離陸律率立略流留硫粒隆竜慮旅虜了僚両寮料涼猟" +
        "力緑倫厘林臨輪隣塁涙累類令例冷励礼鈴隷零霊麗齢暦歴列劣烈裂廉恋練連錬炉路露労廊朗楼浪漏老郎六録論" +
        "腕丑丞串乃之乎也云亘些亦亥亨亮仔伊伎伍伽佃佑伶侃侑俄侠俣俐侶倭俺倶倦倖偲僅傭儲允兎兜其冥冴冶凄凌" +
        "刹劉劫勁勃勾匂勿匡廿卜卯卿厨厩叉叡叢叶只吾呑吻呂哉哨啄唄哩喬喧喰喋嘩嘉嘗噌噂圃圭坐尭坦埼埴堆堰堺" +
        "堵塙塞填壕壬廻弘弛弥彗彦擢孜敦斑斐斡桧栞桔桂桁栖歎此殆毅毘毬燎燦燭燿爪爽瞥瞭矩砦砥砧糊紘紗紐絃紬" +
        "夷奄奈奎套妖娃姪姥娩媛嬉孟宏宋宛宕宥寅寓寵尖尤屑岡峨峻崖崚嵐嵯嵩嶺巌已巳巴巷巽巾帖幌幡庄庇庚庵廟" +
        "彪彬徠忽怜恢恰恕悌惟惚悉惇惹惺惣慧憧憐戊或戚戟戴托按拶拭挨拳捉挺挽掬捲捷捺捻捧掠揃掴摺撒撰撞播撫" +
        "斧斯於旦旭旺昂昊昏昌昧昴晏晃晒晋晟晦晨智暉暢曖曙曝曳曽朋朔杏杖杜李杭杵枕杷枇柑柴柵柿柘柊栃柏柾柚" +
        "桐栗梧梗梓梢梛梯桶梶椛梨梁椅棲椎椋椀楯楚楕椿楠楓椰楢楊榎樺榊榛槙槍槌樫槻樟樋橘樽橙檎檀櫂櫛櫓欣欽" +
        "汀汝汐汎汲沙汰沌沓沫洸洲洵洛浩浬淵淳渚淀淋渥湘湊湛湧溢滉溜漱漕漣澪濡瀕灘灸灼烏焔焚煌煎煤煉熙熊燕" +
        "爾牒牙牟牡牽犀狼猪獅玖玩珂珈珊珀玲琢琉瑛琥琶琵琳瑚瑞瑶瑳瑠璃瓜瓢瓦甥甫畏畠畢畿疋疏痩皐皓眉眸睦瞳" +
        "硯碓碗碩碧磐磯祇祢祐禄禎祷禽禾秦秤稀稔稟稜稽穣穿窄窟窪窺竣竪竺竿笈笹笙笠筈筑箕箔箸篇篠箪簾籾粥粟" +
        "絆絢綺綜綴緋綾綸縞徽繋繍纂纏羚羨翔翠耀而耶耽聡肇肋肘肴胤胡脇脩腔腎膏膳臆臥臼舜舷舵艶芥芹芯芭芙芦" +
        "苑茄苔苺茅茉藤藍蘇蘭虎虹辰辻迂迄辿迪隼雀雁雛雫霞麟麿黎黛鼎亀梅髮髪拔拜拝盃籾徽繋繍臓即帶帯滯滞" +
        "茨茸茜莞荻莫莉菅菫菖萄菩萌莱菱葦葛葵萱葺萩董葡蓋蓑蒔蒐蒼蒲蒙蓉蓮蔭蒋蔦蓬蔓蕎蕨蕉蕃蕪蔽薙蕾蕗藁薩" +
        "蜂蜜蝦蝶螺蝉蟹蝋衿袖袈袴裡裾裟裳襖訊訣註詣詢詮詫誼諏諄誰諒謂諺諦謎讃豹貌貰貼賑赳跨蹄蹟蹴輔輯輿轟" +
        "迦這逞逗逢遥遁遡遜遼邑那祁郁鄭酉酎醇醐醒醍醤采釉釘釜釧鋒鋸錦錐錆錫鍋鍵鍬鎧鎌閃閏閤闇阜阪阿陀隈隙" +
        "靖鞄鞍鞘鞠鞭韓頁頃須頌頓頗頬顛颯餅饗馨馴馳駕駒駿驍魁魯鮎鯉鯛鰯鱒鱗鳩鳶鳳鴨鴻鵜鵬鶴鴎鷲鷺鷹鹿麒麓" +
        "亙凛堯巖彌晄曾檜槇渚猪琢禰祐祿禎穰萠遙?廳庁徴島燈灯盜盗稻稲?徳突難七車瀧滝單単嘆團団彈弾晝昼鑄鋳著" +
         "侠倶倦僅儲卿厩呑哨噌噂堵填娩屑巷廟恢捲揃掴摺撰擢晦楢榊槌樋樽櫛歎溢漣瀕灘灼焔煎煉痩瞥祇祷秤笈箸篇" +
         "芦茨莱葛蒋蓬蔽薩蝉蝋襖註詮諺謎豹辻迂迄辿迦這逗逢遁遡遜祁鄭醤錆隙鞄鞘頬顛餅饗鰯鱒鴎壘塁類禮礼暦歴" +
         "箪簾練錬?郎朗廊録僞偽戲戯虚峽穀碎砕雜雑祉畳孃嬢讓譲釀?増憎藏蔵贈臟十出女聽聴懲鎭鎮轉転傳伝都嶋手" +
         "亞亜惡悪爲為逸榮栄衞衛謁圓円縁薗園應応櫻桜奧奥?横温價価禍悔海壞壊懷懐樂楽渇卷巻陷陥?寛漢氣気祈器" +
         "峡狹狭響曉暁勤謹駈駆勳勲?薫惠恵掲鷄鶏藝芸撃縣県儉倹劍剣險険圈圏檢検顯顕驗験嚴厳廣広恆恒黄國国?黒" +
         "視兒児濕湿實実社者煮壽寿收収臭從従澁渋獸獣縱縦祝暑署?緒諸敍叙將将祥渉燒焼奬奨條条状乘乗淨浄剩剰疊" +
         "醸神眞真寢寝愼慎盡尽粹粋醉酔穗穂?瀬齊斉靜静攝摂節專専戰戦纖繊禪禅祖壯壮爭争莊荘搜捜巣裝装僧層騷騒";
/*
 *  メソッド名		:	$
 *  パラメータ		:	obj : オブジェクトID
 *  戻る値		:	オブジェクト
 *  扱う			:	オブジェクトIDによるとオブジェクトを取得
 */
function $(obj) {
    return document.getElementById(obj);
}

/*
 *  メソッド名		:	isEmpty
 *  パラメータ		:	objId : オブジェクトID
 *  戻る値		:	ブール   true :空白
 *						  false:空白じゃない
 *  扱う			:	入力した文字列が空白かをチェックする。
 */
function isEmpty(objId) {
    if($(objId).value == "") {
        return true;
    }
    return false;
}

/*
 *  メソッド名		:	checkLength
 *  パラメータ		:	objId : オブジェクトID
 				:	len: 長さ
 *  戻る値		:	ブール   true :入力した文字列の長さがlenの内
 *						  false:入力した文字列の長さがlenの内じゃない
 *  扱う			:	入力した文字列の長さがlenの内にあるかをチェックする。
 */
function checkLength(objId, len) {
	if($(objId).value.length <= len){
		return true;
	}
	return false;
}

/*
 *  メソッド名		:	isNE
 *  パラメータ		:	objId : オブジェクトID
 *  戻る値		:	ブール   true :入力した文字列は英数字
 *						  false:入力した文字列は英数字じゃない
 *  扱う			:	入力した文字列が英数字かをチェックする。
 */
function isNE(objId) {
    var reg = /^[A-Za-z0-9]+$/;
    return reg.test($(objId).value);
}

/*
 *  メソッド名		:	isNum
 *  パラメータ		:	objId : オブジェクトID
 *  戻る値		:	ブール   true :入力した文字列は数字
 *						  false:入力した文字列は数字じゃない
 *  扱う			:	入力した文字列が数字かをチェックする。
 */
function isNum(objId) {
    var reg = /^[0-9]+$/;
    return reg.test($(objId).value);
}

/*
 *  メソッド名		:	isBangou
 *  パラメータ		:	objId : オブジェクトID
 *  戻る値		:	ブール   true :入力した文字列は"F9999"の形式
 *						  false:入力した文字列は"F9999"の形式じゃない
 *  扱う			:	入力した文字列が"F9999"の形式かをチェックする。
 */
function isBangou(objId) {
	var reg = /^F[0-9]{4}$/;
    return reg.test($(objId).value);
}

/*
 *  メソッド名		:	isJapanese
 *  パラメータ		:	str : 文字列
 *					type: true :  日本語だけ
 *                        false:  日本語、字母と数字等が含む
 *  戻る値		:	ブール   true :入力した文字列は日本語
 *						  false:入力した文字列は日本語じゃない
 *  扱う			:	入力した文字列が日本語かをチェックする。
 */
function isJapanese(str, type) {
    // 各文字を取得します。
    for (var lp=0; lp < str.length; lp++) {
        // １つの文字を取得します。
        var ucode=str.charAt(lp);
        // 単一文字は平假名片假名をチェックする。
        if (kana.indexOf(ucode) != -1) {
            // 次の文字を取得する。
            continue;
        }
        // １つの文字をチェックします。
        if (s1.indexOf(ucode) != -1) {
            // 次の文字の取得。
            continue;
        }
        // 数字と字母を判断する。
        if (type == false) {
            // 単一文字をチェックする。
            if (englishNumberText.indexOf(ucode) != -1) {
                // 次の文字を取得する。
                continue;
            }
        }
        // 日本語ではない場合は、falseを戻します。
        return false;
    }
    // 日本語である場合は、trueを戻します。
    return true;
}