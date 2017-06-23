<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\nilai;
use App\Matkul;
use DB;

class NilaiController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

    }

    public function findByMatkul(Request $request, $id){
        $idUser = $request->user()->id;
        $matkul = DB::table('matkul')
            ->where('id', '=', $id)->first();


        if($matkul){
            $matkulOBJ = Matkul::find($id);
            $nilai = $matkulOBJ->nilai;
            return response()->json($nilai);
        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
            return response()->json($msg);
        }   
        
       
    }
    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $userId = $request->user()->id;
        $matkul = Matkul::find($request->matkul_id);

        $msg = '';
        $dataArray = explode("/", $request->tanggal);
        $dataFormat = $dataArray[2].'-'.$dataArray[1].'-'.$dataArray[0];

        if($matkul){

            $nilai = Nilai::create([
                'matkul_id' => $request->matkul_id,
                'deskripsi' => $request->deskripsi,
                'nilai' => $request->nilai,
                'tanggal' => $dataFormat
            ]);

            $msg = ['erro' => false, 'msg' => 'nilai terdaftar'];

        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
        }
        return response()->json($msg);
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

        $userId = $request->user()->id;
        $matkul = Matkul::find($request->matkul_id);
        $nilai = Nilai::find($id);

        $msg = '';
        $dataArray = explode("/", $request->tanggal);
        $dataFormat = $dataArray[2].'-'.$dataArray[1].'-'.$dataArray[0];

        if($matkul && $matkul->user->id == $userId){


            if($nilai && $nilai->matkul->id == $request->matkul_id){

                $nilai->deskripsi = $request->deskripsi;
                $nilai->nilai = $request->nilai;
                $nilai->tanggal = $dataFormat;
                $nilai->save();
                $msg = ['erro' => false, 'msg' => 'nilai diperbaharui'];

            }else{
                $msg = ['erro' => true, 'msg' => 'nilai tidak ditemukan'];

            }

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
        $matkulID = $request->header("matkulID");
        $userId = $request->user()->id;
        $matkul = Matkul::find($matkulID);
        $nilai = Nilai::find($id);

        $msg = '';

        if($matkul && $matkul->user->id == $userId){


            if($nilai && $nilai->matkul->id == $matkulID){

                $nilai->delete();
                $msg = ['erro' => false, 'msg' => 'nilai terhapus'];

            }else{
                $msg = ['erro' => true, 'msg' => 'nilai tidak ditemukan'];

            }

        }else{
            $msg = ['erro' => true, 'msg' => 'matkul tidak ditemukan'];
        }

        return response()->json($msg);
    }
}
