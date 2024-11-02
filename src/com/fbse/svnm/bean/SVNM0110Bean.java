/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :ログイン
 * プログラム名        :登録画面
 * モジュール名        :SVNM0110Bean.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.17
 * 機能概要          :システムユーザーの登録
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)張建君
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.bean;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import com.fbse.svnm.base.BaseBean;

/**
 * <HR>
 * 登録画面の入力値の取得及び入力値有効性のチェック。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>登録画面の入力値を取得する。</li>
 * <li>入力値のgetとsetメソッドを生成する。</li>
 * <li>入力値有効性のチェックを行う。</li>
 * <li>画面値をリセットする。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0110Bean extends BaseBean {

    /** システムユーザー番号の宣言と初期化。 */
    private String userId = "";

    /** システムユーザーパスワードの宣言と初期化。 */
    private String password = "";

    /** ログインフラグの宣言と初期化。 */
    private String tourouku = "";

    /**
     * システムユーザーパスワードを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * システムユーザーパスワードを取得する。
     * @param    なし
     * @return    String    システムユーザーパスワード
     * @exception    なし
     */
    public String getPassword() {
        return password;
    }

    /**
     * システムユーザーパスワードを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * システムユーザーパスワードを設定する。
     * @param    password    システムユーザーパスワード
     * @return    なし
     * @exception    なし
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * システムユーザー番号を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * システムユーザー番号を取得する。
     * @param    なし
     * @return    String    システムユーザー番号
     * @exception    なし
     */
    public String getUserId() {
        return userId;
    }

    /**
     * システムユーザー番号を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * システムユーザー番号を設定する。
     * @param    userId    システムユーザー番号
     * @return    なし
     * @exception    なし
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ログインフラグを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * ログインフラグを取得する。
     * @param    なし
     * @return    String    ログインフラグ
     * @exception    なし
     */
    public String getTourouku() {
        return tourouku;
    }

    /**
     * ログインフラグを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * ログインフラグを設定する。
     * @param    tourouku    ログインフラグ
     * @return    なし
     * @exception    なし
     */
    public void setTourouku(String tourouku) {
        this.tourouku = tourouku;
    }

    /**
     * 画面情報をリセットする。
     * <p>
     * <b>処理概要:</b><br>
     * 画面情報をリセットする。
     * @param    mapping    マッピング
     * @param    request    リクエスト
     * @return    なし
     * @exception    なし
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.userId = "";
        this.password = "";
    }

    /**
     * 入力されたデータを検証する。
     * <p>
     * <b>処理概要:</b><br>
     * 入力されたデータを検証する。
     * @param    mapping    マッピング
     * @param    request    リクエスト
     * @return    ActionErrors    エラーメッセージ
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
        // ログを出力する。
        log.printLog("INFO", "SVNM0110Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        // ActionErrors対象の宣言。
        ActionErrors errors = new ActionErrors();
        // ログインボタンを押下する場合。
        if (tourouku.equals("true")) {
            // 従業員番号を入力しない場合。
            if (this.userId == null || this.userId.equals("")) {
                // エラーメッセージを設定する
                errors.add("", new ActionMessage("E001", "従業員番号"));
                // 「従業員番号 」テクストボックスの背景色を赤く表示して、、フォーカスを合わせる。
                setHidError(svnmError("SVNM0110Bean", "userId"));
            }
            // 従業員番号 の内容形式ではない場合
            else if (!isBangou(userId)) {
                // エラーメッセージを設置する。
                errors.add("", new ActionMessage("E017", "従業員番号"));
                // 「従業員番号 」テキストボックスに赤になって、フォーカスを合わせる。
                setHidError(svnmError("SVNM0110Bean", "userId"));
            }
            // パスワードを入力しない場合。
            else if (this.password == null || this.password.equals("")) {
                // エラーメッセージを設置する。
                errors.add("", new ActionMessage("E001", "パスワード"));
                // パスワードテキストボックスに赤になって、フォーカスを合わせる。
                setHidError(svnmError("SVNM0110Bean", "password"));
            }
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0110Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        // ActionErrors対象を戻る。
        return errors;
    }
}