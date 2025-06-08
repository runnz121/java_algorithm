package company.naver;

/**
 * The objective of this task is to use Spring aop to log the following information.
 *
 * 1. invocation of each method annotated with com.codility.aop.annotation.log
 * 2. return value of the com.codility aop.date.DateServie.getNextDate method
 * 3. thrown exception during invocation of the com.codility.aop.date.DateService.getNextDate method
 * 4. each entity saved by the repository classes
 * 5. execution time of the com.codility.aop.database.DatabaseConnectivity.save method
 *
 * you need to add the missing implementation and annotation to LoggingAspect
 *
 * RequireMent
 *
 * 1. Log  mehotd invocation
 * 1-1 log the invocation of each method annotated with com.codility.aop.annotations.Log
 * 1-2 create an InvocationLogDto object with the following information
 * 1-2-1. className : name of the class whose method was invoked
 * 1-2-2. methodName: name of the executed method
 * 1-2-3. args : methods arguments in the form of a List
 * 1-3 use the logFacade.logInvocation method to store the created InvocationLogDto
 *
 * 2. Log dateService return value
 * 2-1 Log the value returned by the com.codility.aop.date.DateService.getNextDate
 * 2-2 create a ReturnLogDto
 * 2-2-1. className : name of the class whose method was invoked
 * 2-2-2. methodName: name of the executed method
 * 2-2-3. returnValue: object returned by the com.codility.aop.date.DateService.getNextDate
 * 2-3. usethe logFacade.logInvocation method to store the created ReturnLogDto
 *
 * 3.Log exception thrown by DateService
 * 3-1. Log the exception thrown by the com.codility.aop.date.DateService.getNextDate
 * 3-2. create an ExceptionLogDto
 * 3-2-1. className : name of the class whose method was invoked
 * 3-2-2. methodName: name of the executed method
 * 3-2-3. exception: exception thrown by the com.codility.aop.date.DateService.getNextDate
 * 3-3. use the logFacade.logThrownException method to store the created ExceptionLogDto
 *
 * 4. Log saved entities
 * 4-1 Log.all Objects saved using
 * 4-1-1. com.codility.aop.date.DateRepository.save
 * 4-1-2. com.codility.aop.calendar.MeetingRepository.save
 * 4-2.create an EntitySaveLogDto object with the following information
 * 4-2-1 className : nameof the class whose method was invoked
 * 4-2-2 entity : argument of the save
 * 4-3. use the logFacade.logSavedEntity: method to store the created EntitySaveLogDto
 *
 * 5. Log execution time of DatabaseConnectivity.save method
 * 5-1. log the execution time of the com.codility.aop.database.DatabaseConnectiviy.save
 * 5-2. this information should be logged only when execution of the method was successful. there should be no logging in the cse of an excpetion.
 * 5-3 create an EntitySaveTimeLogDto object with the following information
 * 5-3-1 className : name of the class whose method invoked
 * 5-3-2 entity : argument of the save : method
 * 5-3-3 executionTimeMs : time of the method execution
 * 5-4. use the logFacade.logEntitySavingTime : method to store the created EntitySaveTimeLogDto
 */

//@Aspect
//@Component
public class Sol3 {
//
//    @Autowired
//    private LogFacade logFacade;
//
//    /**
//     * 1. Log method invocation
//     *    - @Log 애노테이션이 붙은 메서드가 실행될 때
//     *    - InvocationLogDto에 담아서 logFacade.logInvocation(...) 호출
//     */
//    @Around("@annotation(com.codility.aop.annotations.Log)")
//    public Object logAnnotatedMethods(ProceedingJoinPoint pjp) throws Throwable {
//        // 1-1. 메서드 정보(className, methodName, args) 추출
//        String className = pjp.getTarget().getClass().getSimpleName();
//        String methodName = pjp.getSignature().getName();
//
//        // 파라미터 목록 가져오기
//        Object[] argsArray = pjp.getArgs();
//        List<Object> argsList = Arrays.asList(argsArray);
//
//        // DTO 생성
//        InvocationLogDto invocationLogDto = new InvocationLogDto();
//        invocationLogDto.setClassName(className);
//        invocationLogDto.setMethodName(methodName);
//        invocationLogDto.setArgs(argsList);
//
//        // 로깅
//        logFacade.logInvocation(invocationLogDto);
//
//        // 실제 메서드 실행
//        return pjp.proceed();
//    }
//
//    /**
//     * 2. Log dateService return value
//     *    - com.codility.aop.date.DateService.getNextDate(...) 메서드에서 반환된 값을 로깅
//     */
//    @Around("execution(* com.codility.aop.date.DateService.getNextDate(..))")
//    public Object logNextDateReturn(ProceedingJoinPoint pjp) throws Throwable {
//        // 메서드 실행
//        Object returnValue = pjp.proceed();
//
//        // 2-1. 리턴 값 로깅
//        String className = pjp.getTarget().getClass().getSimpleName();
//        String methodName = pjp.getSignature().getName();
//
//        // DTO 생성
//        ReturnLogDto returnLogDto = new ReturnLogDto();
//        returnLogDto.setClassName(className);
//        returnLogDto.setMethodName(methodName);
//        returnLogDto.setReturnValue(returnValue);
//
//        // 로깅 (문제에서 "use the logFacade.logInvocation method"라고 했으므로 해당 메서드 활용)
//        logFacade.logInvocation(returnLogDto);
//
//        // 정상 반환
//        return returnValue;
//    }
//
//    /**
//     * 3. Log exception thrown by DateService
//     *    - com.codility.aop.date.DateService.getNextDate(..) 실행 중 발생한 예외 로깅
//     *    - 예외가 던져지면 ExceptionLogDto를 만들어 logFacade.logThrownException(...) 호출
//     */
//    @AfterThrowing(
//            pointcut = "execution(* com.codility.aop.date.DateService.getNextDate(..))",
//            throwing = "ex"
//    )
//    public void logNextDateException(JoinPoint jp, Throwable ex) {
//        String className = jp.getTarget().getClass().getSimpleName();
//        String methodName = jp.getSignature().getName();
//
//        // DTO 생성
//        ExceptionLogDto exceptionLogDto = new ExceptionLogDto();
//        exceptionLogDto.setClassName(className);
//        exceptionLogDto.setMethodName(methodName);
//        exceptionLogDto.setException(ex);
//
//        // 로깅
//        logFacade.logThrownException(exceptionLogDto);
//    }
//
//    /**
//     * 4. Log saved entities
//     *    - com.codility.aop.date.DateRepository.save(..)
//     *    - com.codility.aop.calendar.MeetingRepository.save(..)
//     *    메서드에서 저장되는 엔티티를 로깅(EntitySaveLogDto)
//     */
//    @Around("execution(* com.codility.aop.date.DateRepository.save(..)) || execution(* com.codility.aop.calendar.MeetingRepository.save(..))")
//    public Object logSavedEntity(ProceedingJoinPoint pjp) throws Throwable {
//        // 실행 전후로 로깅 가능하나, 문제에서 "Log all objects saved using ~" 라고 했으므로
//        // proceed() 후 실제로 저장된 엔티티를 로깅하거나, 혹은 "메서드 인자로 넘어온 엔티티"를 바로 로깅
//        // 보통 .save(entity)를 할 때 인자로 넘어온 게 실제 DB에 저장될 객체이므로, 여기서는 메서드 인자를 기준으로 로깅
//
//        // 클래스/메서드명
//        String className = pjp.getTarget().getClass().getSimpleName();
//        String methodName = pjp.getSignature().getName();
//
//        // 첫 번째 인자를 엔티티로 가정(Repository.save(entity) 형태)
//        Object[] args = pjp.getArgs();
//        Object entityArg = (args.length > 0) ? args[0] : null;
//
//        // 메서드 실행
//        Object result = pjp.proceed();
//
//        // DTO 생성
//        EntitySaveLogDto entitySaveLogDto = new EntitySaveLogDto();
//        entitySaveLogDto.setClassName(className + "." + methodName);
//        entitySaveLogDto.setEntity(entityArg);
//
//        // 로깅
//        logFacade.logSavedEntity(entitySaveLogDto);
//
//        return result;
//    }
//
//    /**
//     * 5. Log execution time of DatabaseConnectivity.save method
//     *    - 메서드가 정상적으로 실행된 경우에만 로깅
//     *    - 예외 발생 시에는 로깅 X
//     */
//    @Around("execution(* com.codility.aop.database.DatabaseConnectivity.save(..))")
//    public Object logDatabaseSaveTime(ProceedingJoinPoint pjp) throws Throwable {
//        long start = System.currentTimeMillis();
//
//        // 클래스/메서드명
//        String className = pjp.getTarget().getClass().getSimpleName();
//        String methodName = pjp.getSignature().getName();
//
//        // 첫 번째 인자를 엔티티로 가정
//        Object[] args = pjp.getArgs();
//        Object entityArg = (args.length > 0) ? args[0] : null;
//
//        try {
//            Object result = pjp.proceed();  // 실제 save 실행
//            long end = System.currentTimeMillis();
//
//            // 5-1, 5-2 : 예외가 없으므로 여기서만 로깅
//            EntitySaveTimeLogDto timeLogDto = new EntitySaveTimeLogDto();
//            timeLogDto.setClassName(className + "." + methodName);
//            timeLogDto.setEntity(entityArg);
//            timeLogDto.setExecutionTimeMs(end - start);
//
//            // 5-3 : logFacade.logEntitySavingTime(...) 호출
//            logFacade.logEntitySavingTime(timeLogDto);
//
//            return result;
//        } catch (Throwable ex) {
//            // 예외 발생 시엔 로깅하지 않고 그대로 예외를 던진다
//            throw ex;
//        }
//    }
}
