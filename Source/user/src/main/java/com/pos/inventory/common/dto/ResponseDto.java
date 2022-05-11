/**
 ***************************************************************
 * Copyright(C) 2019 CodeLantic.
 * All rights reserved.
 * <p>
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF AUXENTA INC.
 * <p>
 * This copy of the Source Code is intended for CodeLantic's
 * internal use only and is intended for view by persons duly
 * authorized by the management of CodeLantic.
 * No part of this file may be reproduced or distributed in any form or by any
 * means without the written approval of the Management of CodeLantic.
 * <p>
 ***************************************************************
 */
package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Response DTO
 *
 * Created by prem on Oct 9, 2017.
 *
 */
@Data
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 6953978110388779136L;

	private boolean status = true;
	private int errorCode;
	private String errorDescription;
	private Object responseDto;



}
