package com.nttuyen.android.base.http;

import com.nttuyen.android.base.Callback;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author nttuyen266@gmail.com
 */
public class HTTP {
	public static String ERROR_EXCEPTION = "error_exception";
	public static String ERROR_RESPONSE = "error_response";

	static DefaultHttpClient client = null;
	static HttpClient createHttpClient() {
		if(client == null) {
			synchronized (HTTP.class) {
				if(client == null) {
					HttpParams params = new BasicHttpParams();
					HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
					HttpProtocolParams.setContentCharset(params, org.apache.http.protocol.HTTP.UTF_8);

					params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, new Integer(60000));
					params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, new Integer(60000));
					params.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);

					client = new DefaultHttpClient(params);
				}
			}
		}
		return client;
	}

	public static void get(String url, Map<String, String> data, Response response, Callback onSuccess, Callback onFailure) {
		execute("GET", url, data, response, onSuccess, onFailure);
	}
	public static <D> void post(String url, D data, Response response, Callback onSuccess, Callback onFailure) {
		execute("POST", url, data, response, onSuccess, onFailure);
	}
	public static <D> void put(String url, D data, Response response, Callback onSuccess, Callback onFailure) {
		handError(onFailure, ERROR_EXCEPTION, new UnsupportedOperationException());
	}

	static <D> void execute(String method, String url, D data, Response response, Callback onSuccess, Callback onFailure) {
		if(method == null || url == null) {
			handError(onFailure, ERROR_EXCEPTION, new IllegalArgumentException("Method and URL must not null"));
		}

		HttpRequestBase request = createRequest(method, url, data);

		if(request == null) {
			handError(onFailure, ERROR_EXCEPTION, new UnsupportedOperationException("Method: " + method + " is not supported now"));
		}

		execute(request, response, onSuccess, onFailure);
	}

	static void execute(HttpRequestBase request, Response response, Callback onSuccess, Callback onFailure) {
		InputStream result = null;
		try {
			HttpClient client = createHttpClient();
			HttpResponse res = client.execute(request);
			int code = res.getStatusLine().getStatusCode();
			if(code == 200) {
				HttpEntity entity = res.getEntity();
				result = entity.getContent();
				response.parse(result);
				//Success
				if(onSuccess != null) {
					onSuccess.execute();
				}
			} else {
				if(onFailure != null) {
					handError(onFailure, ERROR_RESPONSE, null, code);
				}
			}
		} catch (Exception ex) {
			if(onFailure != null) {
				onFailure.execute(ERROR_EXCEPTION, ex);
			}
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	static <D> HttpRequestBase createRequest(String method, String url, D data) {
		HttpRequestBase request = null;
		if("GET".equalsIgnoreCase(method)) {
			Map<String, String> map = (Map<String, String>)data;
			request = createGetRequest(url, map);
		} else if("POST".equalsIgnoreCase(method)) {
			request = createPostRequest(url, data);
		}
		return request;
	}
	static HttpGet createGetRequest(String url, Map<String, String> data) {
		if(url == null) {
			throw new IllegalArgumentException("url must not null");
		}

		StringBuilder builder = new StringBuilder(url);

		if(data != null && !data.isEmpty()) {
			if(builder.indexOf("?") != -1) {
				builder.append('?');
			} else {
				builder.append('&');
			}
			for(Map.Entry<String, String> entry : data.entrySet()) {
				builder.append(entry.getKey())
						.append('=')
						.append(entry.getValue())
						.append('&');
			}
			builder.deleteCharAt(builder.length() - 1);
		}

		HttpGet get = new HttpGet(builder.toString());
		return get;
	}
	static <T> HttpPost createPostRequest(String url, T data) {
		if(url == null) {
			throw new IllegalArgumentException("URL must be not null");
		}

		HttpEntity entity = null;
		//Post form
		if(data != null) {
			if(data instanceof Map) {
				Map map = (Map)data;
				List<NameValuePair> values = new LinkedList<NameValuePair>();
				Set<Map.Entry> entries = map.entrySet();
				for(Map.Entry entry : entries) {
					NameValuePair pair = new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue());
					values.add(pair);
				}
				try {
					entity = new UrlEncodedFormEntity(values, org.apache.http.protocol.HTTP.UTF_8);
				} catch (UnsupportedEncodingException ex) {
					ex.printStackTrace();
					throw new IllegalArgumentException("not support utf-8", ex);
				}
			} else if(data instanceof byte[]) {
				byte[] bytes = (byte[])data;
				entity = new ByteArrayEntity(bytes);
			} else if(data instanceof String) {
				String s = (String)data;
				try {
					entity = new StringEntity(s, org.apache.http.protocol.HTTP.UTF_8);
				} catch (UnsupportedEncodingException ex) {
					ex.printStackTrace();
					throw new IllegalArgumentException("not support utf-8", ex);
				}
			} else if(data instanceof HttpEntity) {
				entity = (HttpEntity)data;
			}

			if(entity == null) {
				throw new IllegalArgumentException("Unsupported this data");
			}
		}

		HttpPost post = new HttpPost(url);
		if(entity != null) {
			post.setEntity(entity);
		}

		return post;
	}

	static void handError(Callback callback, String type, RuntimeException exception, Object... params) {
		if(exception != null) {
			if(callback != null) {
				callback.execute(type, exception, params);
			}
			throw exception;
		} else {
			if(callback != null) {
				callback.execute(type, params);
			}
		}
	}
}
