//package com.pos.inventory.aop;
//
////import com.poscore.common.dto.UserTypeDto;
//
////import com.poscore.service.bl.PrivilegeServiceBL;
////import com.poscore.service.bl.UserTypeServiceBL;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.pos.inventory.TokenAuthenticationService;
//import com.pos.inventory.common.dto.ResponseDto;
//import com.pos.inventory.common.dto.UserTypeDto;
//import com.pos.inventory.common.enums.Authorize;
//import com.pos.inventory.service.BL.UserTypeServiceBL;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Aspect
//@Configuration
//@Component
//@Slf4j
//@Profile({ "prod", "default", "dev", "qa", "ionos-qa" })
//public class AuthorizedAspect {
//
//	static final String SECRET = "$SqQE=YFFGFHA&RF3__=rzT2*H98M#e!j&ef=v%x?@@_$%_ts%";
//	static final String TOKEN_PREFIX = "Bearer";
//	static final String HEADER_STRING = "Authorization";
//
////    @Autowired
////    PrivilegeServiceBL privilegeServiceBL;
//
////    @Configuration
////    static class UserTypeServiceBLContext {
////        @Bean
////        public UserTypeServiceBL userTypeServiceBL() {
////            return new UserTypeServiceBL();
////        }
////    }
//
//	@Autowired
//	UserTypeServiceBL userTypeServiceBL;
//
//	@Around("execution(* com.poscore.web.v1.controller.*.*(..)) && @annotation(com.poscore.aop.Scope) && @annotation(scope)")
////    @Around(" @annotation(scope)")
//	public Object around(ProceedingJoinPoint joinPoint, Scope scope) throws Throwable {
//		// Advice
//		log.info(" Check for user access ");
//		log.info(" Allowed execution for {}", joinPoint);
//		log.info(" Allowed execution for {}", scope);
//		log.info(" Authorize.valueOf(\"CORE_ADMIN\") --> " + Authorize.valueOf("CORE_ADMIN").getAuthorizeType());
//		log.info(" Authorize.valueOf(\"CORE_ADMIN\") --> " + Arrays.toString(Authorize.values()));
//
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//
////        String token = request.getHeader(HEADER_STRING);
////
////
////        String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
////                .getSubject();
////
////
////        log.info(String.valueOf(user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null));
//
//		Authentication authentication = TokenAuthenticationService.getAuthentication(request);
//		log.info("authentication -------- " + authentication.getPrincipal());
//
////        List<PrivilegeDto> privilegeDtoList = privilegeServiceBL.getPrivilegeDetailsByUserName((String) authentication.getPrincipal());
//		List<UserTypeDto> userTypeDtoList = userTypeServiceBL
//				.getUserTypeByUsername((String) authentication.getPrincipal());
////        log.info("Size of the List<PrivilegeDto> privilegeDtoList");
////        log.info(" Size of the List<PrivilegeDto> privilegeDtoList " +privilegeDtoList.size());
//		log.info(" Size of the List<userTypeDtoList> userTypeDtoList " + userTypeDtoList.size());
//		userTypeDtoList.forEach(x -> log.info(x.getDescription()));
//
//		if (userTypeDtoList != null && !userTypeDtoList.isEmpty()) {
//
////            String[] strings = privilegeDtoList.stream()
////                    .map( x -> {
////                        return x.getPrivilegeDescription();
////                    }).toArray(String[]::new);
//
////            List<String> x = Arrays.stream(scope.value()).map(
////                    x -> x.getAuthorizeType()
////            ).collect(Collectors.toList());
//
//			Set<String> userTypeDescSet = userTypeDtoList.stream().map(x -> {
//				return x.getDescription();
//			}).collect(Collectors.toSet());
//
////            String[] authorizedKeys = scope.key();
////             ----------------------
//			List<String> authorizedKeyList = Arrays.stream(scope.value()).map(y -> y.getAuthorizeType())
//					.collect(Collectors.toList());
//
////            Arrays.stream(strings).forEach(log::info);
//			authorizedKeyList.forEach(log::info);
//
//			List<String> authList = authorizedKeyList.stream().filter(x -> userTypeDescSet.contains(x))
//					.collect(Collectors.toList());
//
//			log.info("======================================================================");
//			authList.forEach(log::info);
//			log.info("======================================================================");
//
////            PrivilegeDto privilegeDto = privilegeDtoList.stream()
////                    .filter( x -> scope.key().equals(x.getPrivilegeCode()) )
////                    .findAny().orElse(null);
////
////            if (privilegeDto != null) {
////                log.info(privilegeDto.toString());
////            } else {
////                log.info("privilegeDto is {}", privilegeDto);
////            }
//
//			if (authList != null && !authList.isEmpty()) {
//				log.info("{} == {}", authorizedKeyList, authList);
//				return joinPoint.proceed();
//			} else {
//				log.info("{} == {}", authorizedKeyList, authList != null && !authList.isEmpty() ? authList : null);
//
//				ResponseDto serviceResponseDto = new ResponseDto();
//				serviceResponseDto.setStatus(Boolean.FALSE);
//				serviceResponseDto.setErrorCode(403);
//				serviceResponseDto.setErrorDescription("Forbidden");
//				return serviceResponseDto;
//			}
//		} else {
//			ResponseDto serviceResponseDto = new ResponseDto();
//			serviceResponseDto.setStatus(Boolean.FALSE);
//			serviceResponseDto.setErrorCode(500);
//			serviceResponseDto.setErrorDescription("Server Error");
//			return serviceResponseDto;
//		}
////        return null;
//	}
//}
