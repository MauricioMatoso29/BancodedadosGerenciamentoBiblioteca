
package Entidades;


public class Usuario {
    String idusuario;
    String unome;
    String ucpf;
    String sexo;
    String tel;
    String senha;

    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", unome=" + unome + ", ucpf=" + ucpf + ", sexo=" + sexo + ", tel=" + tel + ", senha=" + senha + '}';
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getUnome() {
        return unome;
    }

    public void setUnome(String unome) {
        this.unome = unome;
    }

    public String getUcpf() {
        return ucpf;
    }

    public void setUcpf(String ucpf) {
        this.ucpf = ucpf;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
