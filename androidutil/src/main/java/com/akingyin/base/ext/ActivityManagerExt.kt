package com.akingyin.base.ext

import android.app.Activity
import kotlin.reflect.KClass

/**
 * @ Description:
 * @author king
 * @ Date 2019/5/28 12:28
 * @version V1.0
 */
object ActivityManagerExt {

    private val container = arrayListOf<Activity>()

    @Synchronized
    fun   add(activity: Activity){
        container.add(activity)
    }

    @Synchronized
    fun   current():Activity?{
        return  if(container.isEmpty()) null else container[container.lastIndex]
    }


    @Suppress("UNCHECKED_CAST")
    @Synchronized
    fun <T : Activity> findFirst(clazz: KClass<T>): T? = container.firstOrNull { it::class == clazz } as? T

    @Suppress("UNCHECKED_CAST")
    @Synchronized
    fun <T : Activity> findLast(clazz: KClass<T>) : T? = container.lastOrNull {
        it :: class == clazz
    } as? T

    @Suppress("UNCHECKED_CAST")
    @Synchronized
    fun <T : Activity> find(clazz: KClass<T>) : List<T>? = container.filter {
        it ::class == clazz
    }.map {
        it  as T
    }

    @Synchronized
    fun remove(activity: Activity) = container.remove(activity)

    @Synchronized
    fun removeAll() = container.clear()

    @Synchronized
    fun finishFirst(clazz: KClass<*>) = container.firstOrNull { it::class == clazz }?.let { finish(it) }

    @Synchronized
    fun finishLast(clazz: KClass<*>) = container.lastOrNull { it::class == clazz }?.let { finish(it) }

    @Synchronized
    fun finish(clazz: KClass<*>) = container
            .filter { it::class == clazz }
            .forEach { finish(it) }

    @Synchronized
    fun finishExcept(vararg clazz: KClass<*>) {
        if (clazz.isEmpty()) {
            finishAll()
            return
        }
        for (i in container.indices.reversed()) {
            val cur = container[i]
            val b = clazz.any { it == cur::class }
            if (!b) finish(cur)
        }
    }

    @Synchronized
    fun finishAll() {
        container.forEach { it.finish() }
        container.clear()
    }

    @Synchronized
    private fun finish(activity: Activity) {
        container.remove(activity)
        activity.finish()
    }

    @Synchronized
    fun activities(): List<Activity> = container

}