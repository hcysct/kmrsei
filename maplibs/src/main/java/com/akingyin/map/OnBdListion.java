/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.akingyin.map;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/9/8 10:58
 * @ Version V1.0
 */

public interface OnBdListion {

  void   call(MKOLUpdateElement mkolUpdateElement, int status);
}
