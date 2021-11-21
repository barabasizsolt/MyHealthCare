<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Medic extends Model
{
    use HasFactory;
    
    protected $fillable = [
        'name',
        'contact',
        'hired_date'
    ];

    protected $dates = [
        'created_at',
        'updated_at',
    ];

    public function medicalDepartments(){
        return $this->belongsToMany(MedicalDepartment::class, 'medical_department_medic')->withPivot('date', 'hour');
    }
}
