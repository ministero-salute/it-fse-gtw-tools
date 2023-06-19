/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 * 
 * Copyright (C) 2023 Ministero della Salute
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package it.finanze.sanita.fjm;

public class TokenRequestDTO {
	private String config;
	private int durationHours = 24;
	private byte[] p12;
	private String aliasP12;
	private String passwordP12;
	private byte[] pem;
	private byte[] fileToHash;
	
	public String getConfig() { return config; }
	public void setConfig(String config) { this.config = config; }
	
	public String getAliasP12() { return aliasP12; }
	public void setAliasP12(String aliasP12) { this.aliasP12 = aliasP12; }
	
	public String getPasswordP12() { return passwordP12; }
	public void setPasswordP12(String passwordP12) { this.passwordP12 = passwordP12; }
	
	public int getDurationHours() { return durationHours; }
	public void setDurationHours(int durationHours) { this.durationHours = durationHours; }
	
	public byte[] getP12() { return p12; }
	public void setP12(byte[] p12) { this.p12 = p12; }
	
	public byte[] getPem() { return pem; }
	public void setPem(byte[] pem) { this.pem = pem; }

	public byte[] getFileToHash() { return fileToHash; }
	public void setFileToHash(byte[] fileToHash) { this.fileToHash = fileToHash; }

}
