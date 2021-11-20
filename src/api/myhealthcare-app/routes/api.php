<?php

use App\Http\Controllers\AppointmentController;
use App\Http\Controllers\ClientController;
use App\Http\Controllers\FeedbackController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'clients'
], function(){
    Route::get('/{id}', [ClientController::class, 'currentClient'])->name('api.current.client');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'appointments'
], function(){
    Route::get('/client/{id}', [AppointmentController::class, 'clientAppointment'])->name('api.client.appointment');
    Route::get('/{id}', [AppointmentController::class, 'singleAppointment'])->name('api.current.appointment');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'feedbacks'
], function(){
    Route::get('/client/{id}', [FeedbackController::class, 'clientFeedbacks'])->name('api.client.feedback');
    Route::get('/{id}', [FeedbackController::class, 'singleFeedback'])->name('api.current.feedback');
});



