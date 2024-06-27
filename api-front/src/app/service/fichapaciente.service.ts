import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { FichaPaciente } from '../model/FichaPaciente';

@Injectable({
    providedIn: 'root'
})
export class FichapacienteService {

    private url: string = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    selecionar(): Observable<FichaPaciente[]> {
        return this.http.get<FichaPaciente[]>(this.url);
    }

    getEspecialidades(): Observable<any[]> {
        return this.http.get<any>(this.url + '/especialidades');
    }

    getPlanos(): Observable<any[]> {
        return this.http.get<any>(this.url + '/planos');
    }

    cadastrar(obj: FichaPaciente): Observable<FichaPaciente> {
        return this.http.post<FichaPaciente>(this.url +
            `/${obj.id}/especialidade/${obj.especialidade.id}/planoDeSaude/${obj.planoDeSaude.id}`, obj)
            .pipe(catchError(this.handleError));
    }

    editar(obj: FichaPaciente): Observable<FichaPaciente> {
        return this.http.put<FichaPaciente>(this.url +
            `/${obj.id}/especialidade/${obj.especialidade.id}/planoDeSaude/${obj.planoDeSaude.id}`, obj)
            .pipe(catchError(this.handleError));
    }

    remover(id: number): Observable<void> {
        return this.http.delete<void>(this.url + '/' + id);
    }

    buscaPorEspecialidade(id: number): Observable<FichaPaciente[]> {
        return this.http.get<FichaPaciente[]>(this.url + `/fichas/especialidade/${id}`);
    }

    buscaPorPlano(id: number): Observable<FichaPaciente[]> {
        return this.http.get<FichaPaciente[]>(this.url + `/fichas/planos/${id}`);
    }


    private handleError(errorResponse: any) {
        let errorMessage = 'Erro desconhecido.';
        if (errorResponse.error instanceof ErrorEvent) {
            // Erro ocorreu no lado do cliente
            errorMessage = `Erro: ${errorResponse.error.message}`;
        } else {
            // O backend retornou um código de erro
            errorMessage = `Código: ${errorResponse.status}, Mensagem: ${errorResponse.error}`;
        }
        return throwError(() => errorMessage);
    }
}
