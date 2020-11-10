package com.example.flashlight

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class TorchAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.torch_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // 실행할 Intent 작성
    // PendingIntent.getService: 서비스 실행
    val intent = Intent(context, TorchService::class.java)
    val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

    // 위젯을 클릭하면 위에서 정의한 Intent 실행
    // PendingIntent: 어떤 인텐트를 실행할지에 따라서 다른 메서드 사용해야 함
    views.setOnClickPendingIntent(R.id.appwidget_layout, pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}