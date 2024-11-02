package com.fbse.svnm.bean;

import com.fbse.svnm.base.BaseBean;

/**
 * <HR>
 * コンボボックス初期化情報を設定する。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>コンボボックス初期化情報を設定する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張志明
 */
public final class SelectBean extends BaseBean {

    /**
     * コンボボックスの値
     */
    private String code = null;

    /**
     * コンボボックスの表示する値
     */
    private String name = null;

    /**
     * コンボボックスの値を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * コンボボックスの値を取得する。
     * @param    なし
     * @return    String    コンボボックスの値
     * @exception    なし
     */
    public String getCode() {
        return code;
    }

    /**
     * コンボボックスの値を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * コンボボックスの値を設定する。
     * @param    kubunCode    コンボボックスの値
     * @return    なし
     * @exception    なし
     */
    public void setCode(String kubunCode) {
        this.code = kubunCode;
    }

    /**
     * コンボボックスの表示する値を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * コンボボックスの表示する値を取得する。
     * @param    なし
     * @return    String    コンボボックスの表示する値
     * @exception    なし
     */
    public String getName() {
        return name;
    }

    /**
     * コンボボックスの表示する値を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * コンボボックスの表示する値を設定する。
     * @param    kubunName    コンボボックスの表示する値
     * @return    なし
     * @exception    なし
     */
    public void setName(String kubunName) {
        this.name = kubunName;
    }
}