package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.validation.ValidUserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    private UserService  userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Create user lottery")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create user lottery",   content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))
            })
    })
    @PostMapping("/{userId}/lotteries/{ticketId}")
    public User createLotterUser(@PathVariable @Valid @ValidUserId Integer userId, @PathVariable Integer ticketId) {
        return this.userService.createLotteryUserByUserID(userId, ticketId);
    }
    @Operation(summary = "Get user lotteries")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get user lotteries",   content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))
            })
    })
    @GetMapping("/{userId}/lotteries")
    public UserResponse getLotteriesByUserID(@PathVariable Integer userId) {
        return this.userService.getLotteriesByUserID(userId);
    }

    @Operation(summary = "Delete user lottery")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete user lottery",   content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))
            })
    })
    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public LotteryResponse deleteLotterUser(@PathVariable @Valid @ValidUserId Integer userId, @PathVariable Integer ticketId) {
        return  this.userService.deleteUserTicketsByUserIdAndTicket(userId, ticketId);
    }
}
