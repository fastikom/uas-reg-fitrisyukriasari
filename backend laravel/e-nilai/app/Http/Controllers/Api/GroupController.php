<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Group;
use DB;

class GroupController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $idUser = $request->user()->id;
        $groups = DB::table('groups')->where('user_id', '=', $idUser)->get();
        return response()->json($groups);
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

        $group = Group::create([
            'deskripsi' => $request->deskripsi,
            'user_id' => $idUser
        ]);
        return response()->json($group, 201);
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
        $group = DB::table('groups')
            ->where('id', '=', $id)
            ->where('user_id', '=', $idUser)
            ->get();

        return response()->json($group);
     
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
        $group = DB::table('groups')
            ->where('id', '=', $id)
            ->where('user_id', '=', $idUser)
            ->first();

        $msg = "";

        if($group){
            $groupEdit = Group::find($group->id);
            $groupEdit->deskripsi = $request->deskripsi;
            $groupEdit->save();
            $msg = ['erro' => false, 'msg' => 'group diperbaharui'];
        }else{
            $msg = ['erro' => true, 'msg' => 'group tidak ditemukan'];
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
        $group = DB::table('groups')
            ->where('id', '=', $id)
            ->where('user_id', '=', $idUser)
            ->first();

        $msg = "";

        if($group){
            $groupDelete = Group::find($group->id);
            $groupDelete->delete();
            $msg = ['erro' => false, 'msg' => 'group terhapus'];
        }else{
            $msg = ['erro' => true, 'msg' => 'group tidak ditemukan'];
        }

        return response()->json($msg);
    }
}
