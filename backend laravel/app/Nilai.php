<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Nilai extends Model
{
	protected $table = 'nilai';
    protected $fillable = array('id', 'nama', 'nilai', 'matkul', 'created_at', 'updated_at');
}