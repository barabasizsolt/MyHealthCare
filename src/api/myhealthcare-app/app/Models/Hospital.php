<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\MedicalDepartment;

class Hospital extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'contact',
        'description',
        'adress',
        'longitude_coordinate',
        'latitude_coordinate'
    ];

    public function medical_departments(){
        return $this->hasMany(MedicalDepartment::class);
    }
}
