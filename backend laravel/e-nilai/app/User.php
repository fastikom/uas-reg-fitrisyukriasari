<?php

namespace App;

use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use HasApiTokens, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'id', 'name', 'email', 'password',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token',
    ];

    public function groupTunggal(){
        return $this->hasMany('App\Group');
    }

    public function group(){
        return $this->belongsToMany('App\Grupo', 'user_group');
    }

    public function matkulTunggal(){
        return $this->hasMany('App\Matkul');
    }

    public function matkul(){
        return $this->belongsToMany('App\Matkul', 'matkul_user');
    }
}
