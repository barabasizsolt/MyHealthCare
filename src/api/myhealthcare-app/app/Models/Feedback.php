<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Feedback extends Model
{
    use HasFactory;

    protected $fillable = [
        'message',
        'billing'
    ];

    protected $dates = [
        'created_at',
        'updated_at',
    ];

    public function appointment(){
        return $this->belongsTo(Appointment::class);
    }
}