<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

use function PHPSTORM_META\map;

class Hospital extends Model
{
    use HasFactory;

    protected $fillable = [
        'hospital_name',
        'hospital_contact',
        'description',
        'hospital_adress',
        'longitude_coordinate',
        'latitude_coordinate'
    ];
}
