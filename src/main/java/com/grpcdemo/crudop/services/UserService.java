package com.grpcdemo.crudop.services;

import com.grpc.usersproto.*;
import com.grpcdemo.crudop.models.User;
import com.grpcdemo.crudop.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    //setting userId from req object
    @Override
    public void postUser(UserPostReq request, StreamObserver<UserPostRes> responseObserver) {
        //super.postUser(request, responseObserver);
        UserRepository userRepository=new UserRepository();
        User user=new User();

        //parsing request : passing user data to repo
        user.setUserName(request.getUserName());
        user.setUserPhoneNumber(request.getUserPhoneNo());
        user.setUserBalance(request.getUserBalance());
        userRepository.save(user);

        //parsing response : getting data after transaction done in db
        UserPostRes.Builder response=UserPostRes.newBuilder().setUserMsg("User Created");

        //sending response back to client
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    //getting user details from userId
    @Override
    public void getUser(UserGetReq request, StreamObserver<UserGetRes> responseObserver) {
        //super.getUser(request, responseObserver);

        UserRepository userRepository=new UserRepository();
        User user=userRepository.getEmployeeById(request.getId());

        //passing user values to response object
        UserGetRes.Builder response=UserGetRes.newBuilder();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setUserPhoneNo(user.getUserPhoneNumber());
        response.setUserBalance(user.getUserBalance());

        //closing connection after responding
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
