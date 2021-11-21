<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Client extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'address',
        'email',
        'password'
    ];

    protected $dates = [
        'created_at',
        'updated_at',
    ];

    public function appointments(){
        return $this->hasMany(Appointment::class);
    }
}
