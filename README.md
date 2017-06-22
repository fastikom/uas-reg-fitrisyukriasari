# E-Nilai
Fitri Syukriasari - 2014150097 <br>
Gustiani Salimah - 2014150088
# Install

1. Laravel
rename .env.example to .env <br>
composer update / composer install <br>
php artisan key:generate <br>
php artisan serve --port=8081 <br>

2. Angular
npm install
ng serve

3. Mobile
Develop with Android Studio
Edit URL = http://l127.0.0.7:8081/api
------------------------------------------------------
Route API

+--------+-----------+-----------------------------------------+--------------------------------------+----------------------------------------------------------------------------+--------------+
| Domain | Method    | URI                                     | Name                                 | Action                                                                     | Middleware   |
+--------+-----------+-----------------------------------------+--------------------------------------+----------------------------------------------------------------------------+--------------+
|        | GET|HEAD  | /                                       |                                      | Closure                                                                    | web          |
|        | POST      | api/atividade                           | api.getAtividadeStore                | App\Http\Controllers\Api\AtividadeController@store                         | api,auth:api |
|        | GET|HEAD  | api/atividade                           | api.getAtividadeIndex                | App\Http\Controllers\Api\AtividadeController@index                         | api,auth:api |
|        | GET|HEAD  | api/atividade/find-by-cronograma/{id}   | api.findByCronograma                 | App\Http\Controllers\Api\AtividadeController@findByCronograma              | api,auth:api |
|        | PUT       | api/atividade/{id}                      | api.getAtividadeUpdate               | App\Http\Controllers\Api\AtividadeController@update                        | api,auth:api |
|        | DELETE    | api/atividade/{id}                      | api.getAtividadeDestroy              | App\Http\Controllers\Api\AtividadeController@destroy                       | api,auth:api |
|        | GET|HEAD  | api/cronograma                          | cronograma.index                     | App\Http\Controllers\Api\CronogramaController@index                        | api,auth:api |
|        | POST      | api/cronograma                          | cronograma.store                     | App\Http\Controllers\Api\CronogramaController@store                        | api,auth:api |
|        | POST      | api/cronograma/compartilhar             | api.getCronogramaRemoverCompartilhar | App\Http\Controllers\Api\CronogramaController@removerCompartilhar          | api,auth:api |
|        | GET|HEAD  | api/cronograma/create                   | cronograma.create                    | App\Http\Controllers\Api\CronogramaController@create                       | api,auth:api |
|        | DELETE    | api/cronograma/{cronograma}             | cronograma.destroy                   | App\Http\Controllers\Api\CronogramaController@destroy                      | api,auth:api |
|        | GET|HEAD  | api/cronograma/{cronograma}             | cronograma.show                      | App\Http\Controllers\Api\CronogramaController@show                         | api,auth:api |
|        | PUT|PATCH | api/cronograma/{cronograma}             | cronograma.update                    | App\Http\Controllers\Api\CronogramaController@update                       | api,auth:api |
|        | GET|HEAD  | api/cronograma/{cronograma}/edit        | cronograma.edit                      | App\Http\Controllers\Api\CronogramaController@edit                         | api,auth:api |
|        | POST      | api/grupo                               | grupo.store                          | App\Http\Controllers\Api\GrupoController@store                             | api,auth:api |
|        | GET|HEAD  | api/grupo                               | grupo.index                          | App\Http\Controllers\Api\GrupoController@index                             | api,auth:api |
|        | GET|HEAD  | api/grupo/create                        | grupo.create                         | App\Http\Controllers\Api\GrupoController@create                            | api,auth:api |
|        | GET|HEAD  | api/grupo/{grupo}                       | grupo.show                           | App\Http\Controllers\Api\GrupoController@show                              | api,auth:api |
|        | PUT|PATCH | api/grupo/{grupo}                       | grupo.update                         | App\Http\Controllers\Api\GrupoController@update                            | api,auth:api |
|        | DELETE    | api/grupo/{grupo}                       | grupo.destroy                        | App\Http\Controllers\Api\GrupoController@destroy                           | api,auth:api |
|        | GET|HEAD  | api/grupo/{grupo}/edit                  | grupo.edit                           | App\Http\Controllers\Api\GrupoController@edit                              | api,auth:api |
|        | PUT       | api/user                                | api.getUserUpdate                    | App\Http\Controllers\Api\UserController@update                             | api,auth:api |
|        | GET|HEAD  | api/user                                | api.getUserIndex                     | App\Http\Controllers\Api\UserController@index                              | api,auth:api |
|        | POST      | api/user/cadastrar                      | api.postUserCadastrar                | App\Http\Controllers\Api\UserController@cadastrar                          | api          |
|        | GET|HEAD  | api/user/cronogramas                    | api.getUserCronogramas               | App\Http\Controllers\Api\UserController@Cronogramas                        | api,auth:api |
|        | GET|HEAD  | api/user/cronogramas-pessoais           | api.getUserCronogramasPessoais       | App\Http\Controllers\Api\UserController@CronogramasPessoais                | api,auth:api |
|        | GET|HEAD  | api/user/grupos                         | api.getUserGrupos                    | App\Http\Controllers\Api\UserController@grupos                             | api,auth:api |
|        | GET|HEAD  | api/user/grupos-pessoais                | api.getUserGruposPessoais            | App\Http\Controllers\Api\UserController@gruposPessoais                     | api,auth:api |
|        | GET|HEAD  | api/user/logout                         | api.getUserLogout                    | App\Http\Controllers\Api\UserController@logout                             | api,auth:api |

