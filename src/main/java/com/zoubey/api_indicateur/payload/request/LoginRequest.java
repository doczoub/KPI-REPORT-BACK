package com.zoubey.api_indicateur.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
  private String nomuser;

	@NotBlank
	private String motpass;

	public String getNomuser() {
		return nomuser;
	}

	public void setNomuser(String nomuser) {
		this.nomuser = nomuser;
	}

	public String getMotpass() {
		return motpass;
	}

	public void setMotpass(String motpass) {
		this.motpass = motpass;
	}
}
