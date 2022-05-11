package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.RoleDto;
import com.pos.inventory.repository.domain.Role;

@Component
public class RoleTransformer implements BaseTransformer<Role, RoleDto> {

	@Override
	public RoleDto transform(Role role) {
		RoleDto roleDto = null;
		if (role != null) {
			roleDto = new RoleDto();
			roleDto.setRoleId(role.getRoleId());
			roleDto.setRoleName(role.getRoleName());
			roleDto.setRoleDescription(role.getRoleDescription());
			roleDto.setIsActive(role.getIsActive());
		}
		return roleDto;
	}

	@Override
	public Role reverseTransform(RoleDto roleDto) {
		Role role = null;
		if (roleDto != null) {
			role = new Role();
			role.setRoleId(roleDto.getRoleId());
			role.setRoleName(roleDto.getRoleName());
			role.setRoleDescription(roleDto.getRoleDescription());
			role.setIsActive(roleDto.getIsActive());
		}
		return role;
	}

}
