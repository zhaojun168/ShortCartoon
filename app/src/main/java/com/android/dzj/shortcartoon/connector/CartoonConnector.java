package com.android.dzj.shortcartoon.connector;

import android.content.Context;

import com.android.callback.ConnectionCallback;
import com.android.dzj.shortcartoon.entity.Cartoon_Json;
import com.android.dzj.shortcartoon.request.CartoonRequest;
import com.android.dzj.shortcartoon.utils.Constants;
import com.android.dzj.shortcartoon.utils.Util;
import com.android.http.callback.AsyncIncident;
import com.android.util.FileUtils;
import com.android.util.MLog;
import com.android.util.WebRequest2;

import java.util.List;

public class CartoonConnector extends BaseConnector {

	private CartoonRequest request;
	
	public CartoonConnector(Context context) {
		super(context);

		request = new CartoonRequest();
		request.page = CartoonRequest.PAGE;
		request.showapi_appid = CartoonRequest.SHOWAPI_APPID;
//		request.showapi_timestamp = ArticleRequest.SHOWAPI_TIMESTAMP;
		request.type = CartoonRequest.TYPE;
		request.showapi_sign = CartoonRequest.SHOWAPI_SIGN;

	}

	private void doGet(ConnectionCallback callback, final boolean isFirstLoad, final String data_cache_name) {
		super.AsyncRequest(new AsyncIncident() {

			@Override
			public Object incident() {
				String url = formatApiUrl(Constants.CARTOON_HOST_URL, request.page, request.type, request.showapi_appid, Util.getTime(), request.showapi_sign);
				WebRequest2 wr = new WebRequest2();

				byte[] by = wr.SyncGet(url);
				if (by == null) {
					return null;
				}
				
				Cartoon_Json json = (Cartoon_Json) FileUtils.ReadFromJsonData(by, Cartoon_Json.class);
				MLog.e(Constants.TAG, "请求结果--->" + new String(by));

				if(isFirstLoad) {
					FileUtils.saveDataToFile(context, data_cache_name, new String(by));//缓存数据
				}
				return json;
			}
		}, callback);
	}
	
	
	/** page-->页数      time--->时间      type--->类型    isFirstLoad--->是否缓存     data_cache_name--->缓存名称     */
	public void getArticleList(ConnectionCallback callback, int page, String type, boolean isFirstLoad, String data_cache_name ){
		request.page = page;
		request.type = type;
		doGet(callback, isFirstLoad, data_cache_name);
	}
	
	/**
	 * 获取缓存的第一页数据
	* <p>Description: </p> 
	* @return
	 */
//	public List<Article_Inof> getCacheData(String data_cache_name)
//	{
//		Cartoon_Json json = (Cartoon_Json) FileUtils.getCacheData(context, data_cache_name, Cartoon_Json.class);
//		if(null != json)
//			return json.showapi_res_body.pagebean.contentlist;//读取缓存
//		else
//			return null;
//	}

}
