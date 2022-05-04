package gotcp

import (
	"net"
	_ "syscall"
	"time"

	"github.com/sirupsen/logrus"
)

var log = logrus.New()

func DoDial() error {
	log.Info("Entered DoDial")

	conn, err := net.DialTimeout("tcp", "192.168.0.1:80", 10 * time.Second)
	log.Infof("Error is %v", err)

	if err == nil {
		conn.Close()
	}

	return err

	// s, _ := syscall.Socket(syscall.AF_INET, syscall.SOCK_STREAM, syscall.IPPROTO_TCP)
	// var lsa, rsa syscall.Sockaddr

	// lsa = &syscall.SockaddrInet4{Addr: [4]byte{}}
	// sa := &syscall.SockaddrInet4{Port: 443, Addr: [4]byte{192, 168, 0, 1}}
	// rsa = sa

	// syscall.Bind(s, lsa)
	// err := syscall.Connect(s, rsa)
	// syscall.Close(s)

}
