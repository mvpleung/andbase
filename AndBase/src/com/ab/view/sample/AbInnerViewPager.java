/*
 * Copyright (C) 2013 www.418log.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ab.view.sample;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.ListView;
import android.widget.ScrollView;

// TODO: Auto-generated Javadoc
/**
 * 
 * Copyright (c) 2012 All rights reserved
 * ���ƣ�AbInnerViewPager.java 
 * ���������ViewPager������ⲿ�ǿɹ���View��List����scrollView��
 * ���ڲ��ɻ���View���¼���ͻ����
 * @author zhaoqp
 * @date��2013-10-24 ����1:36:45
 * @version v1.0
 */
public class AbInnerViewPager extends ViewPager {

	/** The parent scroll view. */
	private ScrollView parentScrollView;
	
	/** The parent list view. */
	private ListView parentListView;
	
	private GestureDetector mGestureDetector;
	
	/**
	 * ��ʼ������ڲ���ViewPager.
	 *
	 * @param context the context
	 */
	public AbInnerViewPager(Context context) {
		super(context);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}

	/**
	 * ��ʼ������ڲ���ViewPager.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public AbInnerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}
	
	/**
	 * �����������¼�.
	 *
	 * @param ev the ev
	 * @return true, if successful
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev)
				&& mGestureDetector.onTouchEvent(ev);
	}

	/**
	 * ���ø�����View.
	 *
	 * @param flag ���Ƿ��������
	 */
	private void setParentScrollAble(boolean flag) {
		if(parentScrollView!=null){
			parentScrollView.requestDisallowInterceptTouchEvent(!flag);
		}
		
		if(parentListView!=null){
			parentListView.requestDisallowInterceptTouchEvent(!flag);
		}
		
	}

	/**
	 * ��������ScrollView��Ҫ����.
	 *
	 * @param parentScrollView the new parent scroll view
	 */
	public void setParentScrollView(ScrollView parentScrollView) {
		this.parentScrollView = parentScrollView;
	}
	
	/**
	 * ��������ListView��Ҫ����.
	 *
	 * @param parentListView the new parent scroll view
	 */
	public void setParentListView(ListView parentListView) {
		this.parentListView = parentListView;
	}
	
	
	class YScrollDetector extends SimpleOnGestureListener {
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			
			if (Math.abs(distanceX) >= Math.abs(distanceY)) {
				//���ײ�����
				setParentScrollAble(false);
				return true;
			}else{
				setParentScrollAble(true);
			}
			return false;
		}
	}

	

}