package com.pos.inventory.service.BL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.pos.inventory.common.dto.PaginatedResponseDto;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.repository.dao.ProductDao;
import com.pos.inventory.service.client.ReportFeignClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceBL {
	@Autowired
	ReportFeignClient reportFeignClient;

	@Autowired
	Gson gson;

	@Autowired
	ProductDao productDao;

	public byte[] productStockReport(String categoryName) {
		String logoName = "https://lh3.googleusercontent.com/zXpiJ37TgWJOWyXtA0zUGlKIg9jnmtmMFp_ODQC0qbjyUbJDp9iAvUeh-pTSJVYv4J5HGIcAg0PRi8bH2vL4qQMfCoH-gboUilWOxK6pBM5d1cLjtt-YHyjrsc9gDcSE5XEhoHrhH-y69lfU46x_OshedFuOlzl-jzqywwVJyLnlDA9SoxMfs6FDcZ1e2pIDKM4zRQpI0VrYtVV1LNnZtbs03c3RRdzOZEYyqBpBhS0yOSTBWlNeSke8p_Y-ki9Jn5JrQ224VHSdWVkNM6JiAVzww5CDyUxsflmSCe_HzNBkRCIIAdVtTwXO8Ng2_Dn2EMXxx5_cPZJk4wvrtACijnHpBPGiAREyWiozr5vv7tvUKLkAlJgasZaBiHBKtc7bRQ0IP-ZLoknRVVLn3EBseqTvBMUlsEmErKtxmKulQ0ykEm_JuVoM-PIot_BOpCr3H7pdggEBqB3w5bHxxu1RuMprKUkndek600kfbheFxrKteM-5VI4wg_93yrQYqvjMdpj1OUNKhwSInl4Mkqmx2zS-05oCrTAeQARMHW7sXQNbv5SF-JYDAharKTPaSlBU9q6jS4vFEU2eywsjkXzOC5L6MCQ9-LeoIWCRS1ov18lQStbc3NBRxFKIpBkJ87AGVu5CxlvxgyeciuXi_x0yZF2pfUUO3ILM7w6mL9_vzstlZHj7HbEGGpYWI_37JsyzdYHyfE65vr2_jzF-UU9nEjrsPCMvPIFrpGfq1gASCV7yVg=w195-h70-no";
		String[] columns = { "No", "Product Name", "Added Quantity", "Current Quantity", "Unit Price", "Barcode",
				"Gross Weight", "Expiry Date" };
		List<ProductDto> listDto = productDao.getProductDetailsByBarcodeOrProductName(categoryName, null);
		log.info("Stock Details Array {}", listDto);

		String[] listOfArrayData = null;
		List<String[]> listArray = new ArrayList<>();
		for (ProductDto stockDto : listDto) {
			listOfArrayData = new String[10];
			listOfArrayData[0] = stockDto.getProductName();
			if(stockDto.getQuantity() != null) {
				listOfArrayData[1] = stockDto.getQuantity().toString();
			}if(stockDto.getStockLevel() != null) {
				listOfArrayData[2] = stockDto.getStockLevel().toString();
			}if(stockDto.getUnitPrice() != null) {
				listOfArrayData[3] = stockDto.getUnitPrice().toString();
			}if(stockDto.getBarcodeDigits() !=null) {
				listOfArrayData[4] = stockDto.getBarcodeDigits().toString();
			}if(stockDto.getGrossWeight()!=null) {
				listOfArrayData[5] = stockDto.getGrossWeight().toString();
			}if(stockDto.getExpiryDate()!= null) {
				listOfArrayData[6] = stockDto.getExpiryDate().toString();
			}if(stockDto.getDiscount() != null ) {
				listOfArrayData[7] = stockDto.getDiscount().toString();	
			}
			listArray.add(listOfArrayData);
		}

		Object reportObject = reportFeignClient.Report("Product Stock Report", listArray, columns, logoName);
		ResponseEntity<byte[]> responseCast = gson.fromJson(gson.toJson(reportObject), ResponseEntity.class);
		byte[] bytes = gson.fromJson(gson.toJson(responseCast.getBody()), byte[].class);
		return bytes;

	}

}
