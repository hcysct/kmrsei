package com.akingyin.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @ Description:
 *   剪切版相关工具
 * @ Author king
 * @ Date 2017/3/6 10:12
 * @ Version V1.0
 */

public class ClipboardUtils {

  private ClipboardUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 复制文本到剪贴板
   *
   * @param text 文本
   */
  public static void copyText(CharSequence text) {
    ClipboardManager clipboard = (ClipboardManager) KissTools.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
  }

  /**
   * 获取剪贴板的文本
   *
   * @return 剪贴板的文本
   */
  public static CharSequence getText() {
    ClipboardManager clipboard = (ClipboardManager) KissTools.getApplicationContext().getSystemService(
        Context.CLIPBOARD_SERVICE);
    ClipData clip = clipboard.getPrimaryClip();
    if (clip != null && clip.getItemCount() > 0) {
      return clip.getItemAt(0).coerceToText(KissTools.getApplicationContext());
    }
    return null;
  }

  /**
   * 复制uri到剪贴板
   *
   * @param uri uri
   */
  public static void copyUri(Uri uri) {
    ClipboardManager clipboard = (ClipboardManager) KissTools.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    clipboard.setPrimaryClip(ClipData.newUri(KissTools.getApplicationContext().getContentResolver(), "uri", uri));
  }

  /**
   * 获取剪贴板的uri
   *
   * @return 剪贴板的uri
   */
  public static Uri getUri() {
    ClipboardManager clipboard = (ClipboardManager) KissTools.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = clipboard.getPrimaryClip();
    if (clip != null && clip.getItemCount() > 0) {
      return clip.getItemAt(0).getUri();
    }
    return null;
  }

  /**
   * 复制意图到剪贴板
   *
   * @param intent 意图
   */
  public static void copyIntent(Intent intent) {
    ClipboardManager clipboard = (ClipboardManager) KissTools.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    clipboard.setPrimaryClip(ClipData.newIntent("intent", intent));
  }

  /**
   * 获取剪贴板的意图
   *
   * @return 剪贴板的意图
   */
  public static Intent getIntent() {
    ClipboardManager clipboard = (ClipboardManager) KissTools.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = clipboard.getPrimaryClip();
    if (clip != null && clip.getItemCount() > 0) {
      return clip.getItemAt(0).getIntent();
    }
    return null;
  }
}
