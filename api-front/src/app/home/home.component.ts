import { Component, OnInit } from '@angular/core';
import { FichaPaciente } from '../model/FichaPaciente';
import { FichapacienteService } from '../service/fichapaciente.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    listaEspecialidades: any;
    listaPlanos: any;
    apareceEspec: boolean = true;

    constructor(private servico: FichapacienteService) { }

    ngOnInit(): void {
        this.selecionar();

        this.servico.getEspecialidades().subscribe((data: any) => {
            this.listaEspecialidades = data;
        });

        this.servico.getPlanos().subscribe((data: any) => {
            this.listaPlanos = data;
        });
    }

    fichaPaciente = new FichaPaciente();

    btnCadastro: boolean = true;

    fichas: FichaPaciente[] = [];

    tabela: boolean = true;

    selecionar(): void {
        this.servico.selecionar()
            .subscribe(retorno => this.fichas = retorno);
    }

    buscaPorEspecialidade(id: number): void {
        this.servico.buscaPorEspecialidade(id)
            .subscribe(retorno => this.fichas = retorno);
    }

    buscaPorPlano(id: number): void {
        this.servico.buscaPorPlano(id)
            .subscribe(retorno => this.fichas = retorno);
    }

    cadastrar(): void {
        this.servico.cadastrar(this.fichaPaciente)
            .subscribe(retorno => {

                this.fichas.push(retorno);
                this.fichaPaciente = new FichaPaciente();

                alert("Ficha cadastrada com sucesso!");

            },

                (error) => {
                    console.error('Erro ao cadastrar ficha: ', error);
                    alert(error);
                }

            );
    }

    selecionarFicha(pos: number): void {

        this.fichaPaciente = this.fichas[pos];

        this.apareceEspec = false;

        this.btnCadastro = false;

        this.tabela = false;
        this.listaEspecialidades.push(this.fichaPaciente.especialidade);
        this.listaPlanos.push(this.fichaPaciente.planoDeSaude);
    }

    editar(): void {
        this.servico.editar(this.fichaPaciente)
            .subscribe(retorno => {

                let pos = this.fichas.findIndex(obj => {
                    return obj.id == retorno.id;
                });

                this.fichas[pos] = retorno;

                this.btnCadastro = true;

                this.tabela = true;

                this.fichaPaciente = new FichaPaciente();

                this.listaEspecialidades.pop();
                this.listaPlanos.pop();
                this.apareceEspec = true;

                alert('Ficha atualizada!');

            },

                (error) => {
                    this.btnCadastro = true;

                    this.tabela = true;

                    this.fichaPaciente = new FichaPaciente();

                    this.listaEspecialidades.pop();
                    this.listaPlanos.pop();
                    this.apareceEspec = true;

                    this.selecionar();

                    alert(error);
                }
            );
    }

    remover(): void {
        this.servico.remover(this.fichaPaciente.id)
            .subscribe(retorno => {

                let pos = this.fichas.findIndex(obj => {
                    return obj.id == this.fichaPaciente.id;
                });

                this.fichas.splice(pos, 1);

                this.btnCadastro = true;

                this.tabela = true;

                this.fichaPaciente = new FichaPaciente();

                this.listaEspecialidades.pop();
                this.listaPlanos.pop();
                this.apareceEspec = true;
                alert('Ficha removida!');

            });
    }

    cancelar(): void {
        this.selecionar();
        this.fichaPaciente = new FichaPaciente();

        this.listaEspecialidades.pop();
        this.listaPlanos.pop();
        this.apareceEspec = true;
        this.btnCadastro = true;

        this.tabela = true;
    }
}
