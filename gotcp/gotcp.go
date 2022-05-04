package gotcp

import (
	"net"
	"time"

	"github.com/sirupsen/logrus"
)

var log = logrus.New()

func DoDial() error {
	log.Info("Entered DoDial")

	conn, err := net.DialTimeout("tcp", "192.168.0.1:80", 10*time.Second)
	log.Infof("Error is %v", err)

	if err == nil {
		log.Infof("Closing conn")
		err = conn.Close()
	}

	return err
}
