<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\User;
use Auth;
use DB;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $user = $request->user();
        return response()->json($user);
    }
    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {
        $idUser = $request->user()->id;
        $user = DB::table('user')
            ->where('id', '=', $idUser)
            ->first();

        $msg = "";

        if($user){
            $userEdit = User::find($user->id);
            $userEdit->name = $request->name;
            $userEdit->save();
            $msg = ['erro' => false, 'msg' => 'user diperbaharui'];
        }else{
            $msg = ['erro' => true, 'msg' => 'user tidak ditemukan'];
        }

        return response()->json($msg);
    }

    public function daftar(Request $request)

    {
        
        if($request->name == "" || $request->email == "" || $request->password == ""){
            return response()->json(['erro' => true, 'msg' => 'Isi semua kolom']);
        }

        $user = DB::table('user')
            ->where('email', '=', $request->email)
            ->first();

        $msg = "";

        if(!$user){

            User::create([
                'name' => $request->name,
                'email' => $request->email,
                'password' => bcrypt($request->password)
            ]);

            $msg = ['erro' => false, 'msg' => 'Pendaftaran sukses'];
        }else{
            $msg = ['erro' => true, 'msg' => 'E-mail sudah digunakan'];
        }

        return response()->json($msg);
    }

    /**
     * 
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function groupTunggal(Request $request)
    {
        $user = $request->user();
        return response()->json($user->groupPribadi);
    }

    /**
     * 
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function group(Request $request)
    {
        $user = $request->user();
        return response()->json($user->group);
    }

    /**
     * 
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function matkulTunggal(Request $request)
    {
        $user = $request->user();
        return response()->json($user->matkulPribadi);
    }

    /**
     * 
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function matkul(Request $request)
    {
        $user = $request->user();
        return response()->json($user->matkul);
    }

    public function logout(Request $request)
    {

        $request->user()->token()->revoke();

        $json = [
            'erro' => false
        ];
        return response()->json($json, '200');
    }

}
