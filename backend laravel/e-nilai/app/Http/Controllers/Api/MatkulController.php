<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Matkul;
use App\User;
use DB;

class MatkulController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $idUser = $request->user()->id;
        $user = User::find($idUser);

        $matkul = DB::table('matkul')->where('user_id', '=', $idUser)->get();

        return response()->json($matkul);
    }
    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $idUser = $request->user()->id;

        $matkul = Matkul::create([
            'deskripsi' => $request->deskripsi,
            'user_id' => $idUser
        ]);
        return response()->json($matkul, 201);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {

        $idUser = $request->user()->id;
        $matkul = DB::table('matkul')
            ->where('id', '=', $id)
            ->where('user_id', '=', $idUser)
            ->get();

        return response()->json($matkul);
     
    }
    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $idUser = $request->user()->id;
        $matkul = DB::table('matkul')
            ->where('id', '=', $id)
            ->where('user_id', '=', $idUser)
            ->first();

        $msg = "";

        if($matkul){
            $matkulEdit = Matkul::find($matkul->id);
            $matkulEdit->deskripsi = $request->deskripsi;
            $matkulEdit->save();
            $msg = ['erro' => false, 'msg' => 'matkul diperbaharui'];
        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
        }

        return response()->json($msg);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request, $id)
    {
        $idUser = $request->user()->id;
        $matkul = DB::table('matkul')
            ->where('id', '=', $id)
            ->where('user_id', '=', $idUser)
            ->first();

        $msg = "";

        if($matkul){

            $matkulHapus = Matkul::find($matkul->id);

            $deletar = DB::table('nilai')
                ->where('matkul_id', '=', $matkulHapus->id)
                ->delete();

            $deletar = DB::table('matkul_user')
                ->where('matkul_id', '=', $matkulHapus->id)
                ->delete();

            $matkulHapus->delete();
            $msg = ['erro' => false, 'msg' => 'matkul terhapus'];
        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
        }

        return response()->json($msg);
    }


    public function relasi(Request $request)
    {
        $idUser = $request->user()->id;
        $matkul = DB::table('matkul')
            ->where('id', '=', $request->matkul_id)
            ->where('user_id', '=', $idUser)
            ->first();

        $msg = "";

        if($matkul){

            $userrelasi = User::find($request->user_id);

            if($userrelasi){


                $verifikasi = DB::table('matkul_user')
                    ->where('matkul_id', '=', $matkul->id)
                    ->where('user_id', '=', $userrelasi->id)
                    ->first();

                if(!$verifikasi){

                    $matkul = Matkul::find($matkul->id);
                    $userrelasi->matkul()->attach($matkul);
                    $msg = ['erro' => false, 'msg' => 'matkul terelasi'];

                }else{
                    $msg = ['erro' => true, 'msg' => 'matkul telah berelasi dengan pengguna ini'];
                }

            }else{
                $msg = ['erro' => true, 'msg' => 'pengguna tidak ditemukan'];
            }
     

        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
        }

        return response()->json($msg);
    }


    public function removerrelasi(Request $request)
    {
        $idUser = $request->user()->id;
        $matkul = DB::table('matkul')
            ->where('id', '=', $request->matkul_id)
            ->where('user_id', '=', $idUser)
            ->first();

        $msg = "";

        if($matkul){

            $userrelasi = User::find($request->user_id);

            if($userrelasi){


                $verifikasi = DB::table('matkul_user')
                    ->where('matkul_id', '=', $matkul->id)
                    ->where('user_id', '=', $userrelasi->id)->get();

                    

                if(!$verifikasi->isEmpty()){

                    DB::table('matkul_user')
                        ->where('matkul_id', '=', $matkul->id)
                        ->where('user_id', '=', $userrelasi->id)->delete();

                    $msg = ['erro' => false, 'msg' => 'relasi dihapus'];

                }else{
                    $msg = ['erro' => true, 'msg' => 'relasi tidak ditemukan'];
                }

            }else{
                $msg = ['erro' => true, 'msg' => 'pengguna tidak ditemukan'];
            }
     

        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
        }

        return response()->json($msg);
    }
}
