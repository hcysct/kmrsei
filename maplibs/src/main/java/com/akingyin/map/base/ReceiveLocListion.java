/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.akingyin.map.base;

import com.baidu.location.BDLocation;

/**
 * @author king
 * @version V1.0
 * @ Description:
 *
 *
 * @ Date 2017/11/24 12:40
 */
public interface ReceiveLocListion {

  void  onReceiveLocation(BDLocation location);
}
