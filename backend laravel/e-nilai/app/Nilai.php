<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;

class Nilai extends Model
{
    protected $fillable = [
        'matkul_id', 'deskripsi', 'nilai', 'tanggal'
    ];

    public function matkul(){
    	return $this->belongsTo('App\Matkul');
    }

    function getTanggalAttribute($value)
	{
	    return Carbon::parse($value)->format('d/m/Y');
	}
}
