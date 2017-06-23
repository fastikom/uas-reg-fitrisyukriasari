<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Group extends Model
{
    protected $fillable = [
        'deskripsi', 'user_id'
    ];

    public function userTunggal(){
        return $this->belongsTo('App\User');
    }

    public function users(){
        return $this->belongsToMany('App\User', 'user_group');
    }
}
