<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Matkul extends Model
{
    protected $fillable = [
        'deskripsi', 'user_id'
    ];

    public function user(){
        return $this->belongsTo('App\User');
    }

   	public function users(){
        return $this->belongsToMany('App\User', 'matkul_user');
    }

    public function nilai(){
    	return $this->hasMany('App\Nilai');
    }


}
