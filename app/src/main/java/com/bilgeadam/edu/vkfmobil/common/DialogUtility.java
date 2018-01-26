package com.bilgeadam.edu.vkfmobil.common;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bilgeadam.edu.vkfmobil.R;


/**
 * Created by ksmacpro on 30/11/16.
 */

public class DialogUtility {




    public static MaterialDialog showProgress(Context context, String title, String content) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (title != null) {
            builder.title(title);
        }
        if (content != null) {
            builder.content(content);
        }

        builder.progress(true, 0);
        builder.progressIndeterminateStyle(true);
        return builder.show();
    }

    public static MaterialDialog showProgress(Context context, String content) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);

        if (content != null) {
            builder.content(content);
        }
        builder.title("");
        builder.progress(true, 0);
        builder.progressIndeterminateStyle(true);
        return builder.show();
    }

    public static MaterialDialog showProgress(Context context, String title, String content, boolean indeterminateStyle) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (title != null) {
            builder.title(title);
        }
        if (content != null) {
            builder.content(content);
        }

        builder.progress(true, 0);

        builder.backgroundColorRes(R.color.colorPrimary);
        builder.contentColorRes(R.color.white);
        builder.linkColorRes(R.color.white);
        builder.widgetColorRes(R.color.white);
        builder.dividerColorRes(R.color.white);
        builder.progressIndeterminateStyle(indeterminateStyle);
        return builder.show();
    }

    public static void showDialog(Context context, String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title("")
                .content(message)
                .positiveText(R.string.Ok);

        builder.backgroundColorRes(R.color.colorPrimary);
        builder.contentColorRes(R.color.white);
        builder.linkColorRes(R.color.white);
        builder.widgetColorRes(R.color.colorPrimary);
        builder.dividerColorRes(R.color.white);
        builder.positiveColorRes(R.color.white);
        builder.canceledOnTouchOutside(false);
        builder.cancelable(false);
        MaterialDialog dialog = builder.build();
        dialog.show();
    }
    public static void showDialog(Context context, String title, String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(R.string.Ok);

        builder.backgroundColorRes(R.color.colorPrimary);
        builder.contentColorRes(R.color.white);
        builder.linkColorRes(R.color.white);
        builder.widgetColorRes(R.color.colorPrimary);
        builder.dividerColorRes(R.color.white);
        builder.positiveColorRes(R.color.white);
        builder.canceledOnTouchOutside(false);
        builder.cancelable(false);
        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    public static MaterialDialog showDialog(Context context, String title, String message, MaterialDialog.SingleButtonCallback onClickListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (title != null && !title.equalsIgnoreCase("")) {
            builder.title(title);
        }
        builder.content(message);
        builder.cancelable(false);
        builder.positiveText(R.string.Ok);
        if (onClickListener != null) {
            builder.onPositive(onClickListener);
        }
        builder.backgroundColorRes(R.color.colorPrimary);
        builder.contentColorRes(R.color.white);
        builder.titleColorRes(R.color.white);
        builder.linkColorRes(R.color.white);
        builder.widgetColorRes(R.color.colorPrimary);
        builder.dividerColorRes(R.color.white);
        builder.positiveColorRes(R.color.white);
        builder.canceledOnTouchOutside(false);
        builder.cancelable(false);
        MaterialDialog dialog = builder.build();
        dialog.show();
        return dialog;

    }

    public static MaterialDialog showDialog(Context context, String title, String positiveText, String negativeText, String message, MaterialDialog.SingleButtonCallback onClickListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if (title != null && !title.equalsIgnoreCase("")) {
            builder.title(title);
        }
        builder.content(message);
        builder.cancelable(false);
        builder.positiveText(positiveText);
        builder.negativeText(negativeText);
        if (onClickListener != null) {
            builder.onPositive(onClickListener);
        }
        builder.backgroundColorRes(R.color.colorPrimary);
        builder.contentColorRes(R.color.white);
        builder.linkColorRes(R.color.white);
        builder.widgetColorRes(R.color.colorPrimary);
        builder.dividerColorRes(R.color.white);
        builder.positiveColorRes(R.color.white);
        builder.titleColorRes(R.color.white);
        builder.negativeColorRes(R.color.white);
        builder.canceledOnTouchOutside(false);
        builder.cancelable(false);
        MaterialDialog dialog = builder.build();
        dialog.show();
        return dialog;

    }


}
