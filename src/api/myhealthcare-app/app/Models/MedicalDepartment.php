<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\Hospital;

class MedicalDepartment extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'description',
        'contact'
    ];

    protected $dates = [
        'created_at',
        'updated_at',
    ];

    public function hospital(){
        return $this->belongsTo(Hospital::class);
    }

    public function medics(){
        return $this->belongsToMany(Medic::class, 'medical_department_medic');
    }
}
