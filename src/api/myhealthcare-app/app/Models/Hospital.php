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
        'address',
        'longitude_coordinate',
        'latitude_coordinate'
    ];

    protected $dates = [
        'created_at',
        'updated_at',
    ];

    public function medical_departments(){
        return $this->hasMany(MedicalDepartment::class);
    }
}
