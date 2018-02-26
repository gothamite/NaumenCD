package ru.naumen.naumencd.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class RetrofitModule {
	@Provides
	@Singleton
	public Retrofit provideRetrofit(Retrofit.Builder builder) {
		return builder.baseUrl("http://testwork.nsd.naumen.ru/").build();
	}

	@Provides
	@Singleton
	public Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory) {
		return new Retrofit.Builder()
				.addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
				.addConverterFactory(converterFactory);
	}

	@Provides
	@Singleton
	public Converter.Factory provideConverterFactory(Gson gson) {
		return GsonConverterFactory.create(gson);
	}
	@Provides
	@Singleton
	Gson provideGson() {
		return new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.serializeNulls()
				.create();
	}
}
