<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::post('user/daftar', [
	'uses' => 'Api\UserController@daftar',
	'as' => 'api.postUserDaftar'
]);


Route::group(['middleware' => ['auth:api']], function(){

	Route::group(['prefix' => 'user'], function () {

		Route::get('', [
			'uses' => 'Api\UserController@index',
			'as' => 'api.getUserIndex'
		]);

		Route::put('', [
			'uses' => 'Api\UserController@update',
			'as' => 'api.getUserUpdate'
		]);


		Route::get('logout', [
			'uses' => 'Api\UserController@logout',
			'as' => 'api.getUserLogout'
		]);

		Route::get('group-tunggal', [
			'uses' => 'Api\UserController@groupTunggal',
			'as' => 'api.getUserGroupTunggal'
		]);

		Route::get('group', [
			'uses' => 'Api\UserController@group',
			'as' => 'api.getUserGroup'
		]);

		Route::get('matkul-tunggal', [
			'uses' => 'Api\UserController@matkulTunggal',
			'as' => 'api.getUserMatkulTunggal'
		]);

		Route::get('matkul', [
			'uses' => 'Api\UserController@matkul',
			'as' => 'api.getUserMatkul'
		]);

	});


	Route::group(['prefix' => 'nilai'], function () {

		Route::get('', [
			'uses' => 'Api\NilaiController@index',
			'as' => 'api.getNilaiIndex'
		]);

		Route::get('find-by-matkul/{id}', [
			'uses' => 'Api\NilaiController@findByMatkul',
			'as' => 'api.findByMatkul'
		]);


		Route::post('', [
			'uses' => 'Api\NilaiController@store',
			'as' => 'api.getNilaiStore'
		]);

		Route::put('{id}', [
			'uses' => 'Api\NilaiController@update',
			'as' => 'api.getNilaiUpdate'
		]);

		Route::delete('{id}', [
			'uses' => 'Api\NilaiController@destroy',
			'as' => 'api.getNilaiDestroy'
		]);

	});

    Route::resource('group', 'Api\GroupController');
    Route::resource('matkul', 'Api\MatkulController');

	Route::post('matkul/relasi', [
		'uses' => 'Api\MatkulController@relasi',
		'as' => 'api.getMatkulRelasi'
	]);

	Route::post('matkul/relasi', [
		'uses' => 'Api\MatkulController@removerrelasi',
		'as' => 'api.getMatkulRemoverrelasi'
	]);

});


