<?php

use App\Http\Controllers\AppointmentController;
use App\Http\Controllers\ClientController;
use App\Http\Controllers\FeedbackController;
use App\Http\Controllers\HospitalController;
use App\Http\Controllers\MedicalDepartmentController;
use App\Http\Controllers\MedicController;
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
    Route::post('registerClient', [ClientController::class, 'registerClient'])->name('api.register.client');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'appointments'
], function(){
    Route::get('/client/{id}', [AppointmentController::class, 'clientAppointment'])->name('api.client.appointment');
    Route::get('/{id}', [AppointmentController::class, 'singleAppointment'])->name('api.current.appointment');
    Route::post('makeAppointment', [AppointmentController::class, 'makeAppointment'])->name('api.make.appointment');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'feedbacks'
], function(){
    Route::get('/client/{id}', [FeedbackController::class, 'clientFeedbacks'])->name('api.client.feedback');
    Route::get('/{id}', [FeedbackController::class, 'singleFeedback'])->name('api.current.feedback');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'hospitals' 
], function(){
    Route::get('/{medical_dep_name?}', [HospitalController::class, 'all'])->name('api.hospitals.all');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'medicaldepartments' 
], function(){
    Route::get('unique', [MedicalDepartmentController::class, 'uniqueName'])->name('api.dep.all');
    Route::get('hospital/{hospital_id?}', [MedicalDepartmentController::class, 'departmentsForHospitals'])->name('api.depfor.hospital');
});

Route::group([
    'middleware' => 'api',
    'prefix' => 'medics' 
], function(){
    Route::get('/{medical_dep_id?}', [MedicController::class, 'all'])->name('api.medics.all');
});

