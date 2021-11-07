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

    public function hospital(){
        return $this->belongsTo(Hospital::class);
    }
}
